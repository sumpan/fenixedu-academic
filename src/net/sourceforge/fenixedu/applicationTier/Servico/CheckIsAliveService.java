package net.sourceforge.fenixedu.applicationTier.Servico;

import java.io.IOException;
import java.util.Properties;

import net.sourceforge.fenixedu._development.LogLevel;
import net.sourceforge.fenixedu._development.PropertiesManager;
import net.sourceforge.fenixedu.applicationTier.Service;
import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;

import org.apache.log4j.Logger;

public class CheckIsAliveService extends Service {

    private static final Logger logger = Logger.getLogger(CheckIsAliveService.class);

    private static boolean CHECK_DB = false;

    static {
        final String propertiesFilename = "/checkIsAlive.properties";
        final Properties properties = new Properties();
        try {
            PropertiesManager.loadProperties(properties, propertiesFilename);
            final String checkDB = properties.getProperty("script.isAlive.check.db");
            if ("true".equalsIgnoreCase(checkDB)) {
                CHECK_DB = true;
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }

        if (LogLevel.INFO) {
            logger.info("CheckIsAliveService - will check db: " + CHECK_DB);
        }
    }

    public Boolean run() {
        try {
            if (CHECK_DB) {
                checkFenixDatabaseOps();
            }
            return Boolean.TRUE;
        } catch (Throwable t) {
            t.printStackTrace();
            if (LogLevel.FATAL) {
                logger.fatal("Got unexepected exception in check alive service. ", t);
            }
            throw new RuntimeException(t);
        }
    }

    private void checkFenixDatabaseOps() throws ExcepcaoPersistencia {

        final ExecutionYear executionYear = ExecutionYear.readCurrentExecutionYear();

        if (executionYear == null || executionYear.getIdInternal() == null) {
            if (LogLevel.FATAL) {
                logger.fatal("Got a null result checking fenix database.");
            }
            throw new RuntimeException("Problems accesing fenix database! Got a null result.");
        }
    }

}
