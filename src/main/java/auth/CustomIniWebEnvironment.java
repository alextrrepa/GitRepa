package auth;

import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

public class CustomIniWebEnvironment extends IniWebEnvironment {

    @Override
    protected FilterChainResolver createFilterChainResolver() {
        PathMatchingFilterChainResolver filterChainResolver =
                new PathMatchingFilterChainResolver();
        DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();
        for (DefaultFilter filter : DefaultFilter.values()) {
//            System.out.println("Default Filters:::" + filter.name());
        }

        filterChainResolver.setFilterChainManager(filterChainManager);
//        System.out.println("We are in the CustomIniWebEnv !!!");
        return filterChainResolver;
    }
}
