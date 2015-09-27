package auth;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.security.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConfiguration extends Configuration {
    private static DbConfiguration dbConfig;

    protected DbConfiguration() {
        super();
    }

    public static void init() {
        dbConfig = new DbConfiguration();
        Configuration.setConfiguration(dbConfig);
    }

    public static DbConfiguration getDbConfig() {
        return dbConfig;
    }

    public static String controlFlagString(AppConfigurationEntry.LoginModuleControlFlag flag) {
        if (AppConfigurationEntry.LoginModuleControlFlag.REQUIRED.equals(flag)) {
            return "REQUIRED";
        } else if (AppConfigurationEntry.LoginModuleControlFlag.REQUISITE.equals(flag)) {
            return "REQUISITE";
        } else if (AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT.equals(flag)) {
            return "SUFFICIENT";
        } else if (AppConfigurationEntry.LoginModuleControlFlag.OPTIONAL.equals(flag)) {
            return "OPTIONAL";
        } else {
            return "OPTIONAL";
        }
    }

    public void addAppConfigurationEntry(String appName,
                                         AppConfigurationEntry entry) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5454/test\",\"postgres\", \"cezet123\")");
        String sql = "INSERT INTO app_configuration VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, appName);
        preparedStatement.setString(2, entry.getLoginModuleName());
        preparedStatement.setString(3, controlFlagString(entry.getControlFlag()));
    }

    @Override
    public Provider getProvider() {
        return super.getProvider();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public Parameters getParameters() {
        return super.getParameters();
    }

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        return new AppConfigurationEntry[0];
    }

    @Override
    public void refresh() {
        super.refresh();
    }
}