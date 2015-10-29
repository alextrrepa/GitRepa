package auth;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CustomPathMatchingFilter extends PathMatchingFilterChainResolver {
    private CustomFilterChainManager customFilterChainManager;

    public void setCustomFilterChainManager(CustomFilterChainManager customFilterChainManager) {
        this.customFilterChainManager = customFilterChainManager;
        setFilterChainManager(customFilterChainManager);
    }

    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }

        String requestURI = getPathWithinApplication(request);
        System.out.println("RequestUri:::" + requestURI);
        List<String> chainNames = new ArrayList<>();
        for (String pathPattern : filterChainManager.getChainNames()) {
            System.out.println("PathPattern:::" + pathPattern);
            if (pathMatches(pathPattern, requestURI)) {
                chainNames.add(pathPattern);
            }
        }
        if (chainNames.size() == 0) {
            return null;
        }
        return customFilterChainManager.proxy(originalChain, chainNames);
    }
}