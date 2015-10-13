package auth;

import auth.entities.AppConfigEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.apache.log4j.Logger;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag;

public class DBConfiguration extends Configuration {
    private final static Logger log = Logger.getLogger(DBConfiguration.class);
    private static DBConfiguration dbConfig;

    public static void init() {
        dbConfig = new DBConfiguration();
        Configuration.setConfiguration(dbConfig);
    }

    public static DBConfiguration getDbConfig() {
        return dbConfig;
    }

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        if (name == null) {
            throw new NullPointerException("App name was null");
        }
        AuthDaoIF<AppConfigEntity, Long> authDao = new AuthItemHibernateDao<>(AppConfigEntity.class);
        List<AppConfigEntity> config = authDao.getAppConfig(name);
        List<AppConfigurationEntry> entries = new ArrayList<>();
        for (AppConfigEntity entity : config) {
            String loginModuleClass = entity.getLoginModule();
            String controlFlagValue = entity.getLoginModule();
            LoginModuleControlFlag controlFlag = resolveControlFlag(controlFlagValue);
            AppConfigurationEntry entry = new AppConfigurationEntry(loginModuleClass, controlFlag, new HashMap());
            entries.add(entry);
        }
        if (entries.isEmpty()) {
            log.error("No AppConfigurationEntries found for applicationName :" + name);
        }
        return entries.toArray(new AppConfigurationEntry[entries.size()]);
    }

    private LoginModuleControlFlag resolveControlFlag(String name) {
        if (name == null) {
            throw new NullPointerException("Control flag name passed in is null");
        }
        String uppedName = name.toUpperCase(Locale.US);
        switch (uppedName) {
            case "REQUIRED":
                return LoginModuleControlFlag.REQUIRED;
            case "REQUISITE":
                return LoginModuleControlFlag.REQUISITE;
            case "SUFFICIENT":
                return LoginModuleControlFlag.SUFFICIENT;
            case "OPTIONAL":
                return LoginModuleControlFlag.OPTIONAL;
            default:
                return LoginModuleControlFlag.OPTIONAL;
        }
    }

    private String controlFlagString(LoginModuleControlFlag flag) {
        if (LoginModuleControlFlag.REQUIRED.equals(flag)) {
            return "REQUIRED";
        } else if (LoginModuleControlFlag.REQUISITE.equals(flag)) {
            return "REQUISITE";
        } else if (LoginModuleControlFlag.SUFFICIENT.equals(flag)) {
            return "SUFFICIENT";
        } else if (LoginModuleControlFlag.OPTIONAL.equals(flag)) {
            return "OPTIONAL";
        } else {
            return "OPTIONAL";
        }
    }
}
