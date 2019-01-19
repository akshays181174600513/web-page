package MyDBAuthenticationService;

import java.util.ArrayList;

import shoppingcart.entity.account;

public class authentication implements userdetail {
 
    @Autowired
    private shoppingart.dao.accdao accdao;
 
    public <userdetail> userdetail loadUserByUsername(String username) throws UsernameNotFoundException {
        account account = accdao.findAccount(username);
        System.out.println("Account= " + account);
 
        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + username + " was not found in the database");
        }
 
     
        String role = account.getUserRole();
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
 
 
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
 
        grantList.add(authority);
 
        boolean enabled = account.isActive();
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
 
        userdetail userdetail = (userdetail) new user(account.getUserName(), //
                account.getPassword(), enabled, accountNonExpired, //
                credentialsNonExpired, accountNonLocked, grantList);
 
        return userdetail;
    }
 
}
