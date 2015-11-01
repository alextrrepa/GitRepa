package auth;

import auth.entities.RoleEntity;
import auth.entities.UrlFilterEntity;
import dao.AuthDaoIF;
import dao.AuthItemHibernateDao;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

import java.util.List;
import java.util.Set;

public class CustomIniWebEnvironment extends IniWebEnvironment {

    @Override
    protected FilterChainResolver createFilterChainResolver() {
        PathMatchingFilterChainResolver filterChainResolver =
                new PathMatchingFilterChainResolver();
        DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();
/*        for (DefaultFilter filter : DefaultFilter.values()) {
            System.out.println("Default Filters:::" + filter.name());
        }*/

        AuthDaoIF<UrlFilterEntity, Long> urlDao = new AuthItemHibernateDao<>(UrlFilterEntity.class);
        List<UrlFilterEntity> urlsFilters = urlDao.getUrlFilters();
        for (UrlFilterEntity urls : urlsFilters) {
            Set<RoleEntity> roles = urls.getRoles();
            for (RoleEntity r : roles) {
                System.out.println(urls.getUrl() + " " + r.getRole());
                filterChainManager.addToChain(urls.getUrl(), "roles", r.getRole());
            }
        }

        filterChainResolver.setFilterChainManager(filterChainManager);
        System.out.println("@@@ We are in the CustomIniWebEnv !!!");
        return filterChainResolver;
    }
}
