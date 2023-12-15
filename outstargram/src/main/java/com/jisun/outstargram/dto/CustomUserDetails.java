package com.jisun.outstargram.dto;

/*dto*/

import com.jisun.outstargram.entity.Member;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private Member loggedMember; //콤포지션
    private Map<String, Object> attributes;


    //생성자 있어야함~!!
    //UserDetails : 홈페이지에서 회원가입한 유저
    public CustomUserDetails(Member loggedMember){
        this.loggedMember = loggedMember;
    }

    //OAuth2User : sns계정으로 회원가입한 유저
    public CustomUserDetails(Member loggedMember, Map<String, Object> attributes) {
        this.loggedMember = loggedMember;
        this.attributes = attributes;
    }

    //OAuth2User
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
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


    //OAuth2User
    @Override
    public String getName() {
        return (String)attributes.get("name");
    }



}
