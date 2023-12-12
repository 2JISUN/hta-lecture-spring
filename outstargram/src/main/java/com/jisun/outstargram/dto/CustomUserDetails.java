package com.jisun.outstargram.dto;

/*dto*/

import com.jisun.outstargram.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Data //getter & setter
public class CustomUserDetails implements UserDetails {

    private Member loggedMember; //콤포지션

    //생성자 있어야함~~
    public CustomUserDetails(Member loggedMember){
        this.loggedMember = loggedMember;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority(){

            @Override
            public String getAuthority() {
                return loggedMember.getRole().toString(); //우리가 임의로 글자로 바꿔줌~~
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return loggedMember.getPassword();
    }

    @Override
    public String getUsername() {
        return loggedMember.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
