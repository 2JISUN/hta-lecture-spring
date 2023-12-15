package com.jisun.outstargram.social;



public interface SocialUserInfo {
    public String getProvider(); //구글, 깃허브, 네이버, 카카오
    public String getProviderId(); //google_userId
    public String getEmail(); //snm03097@gamil.com
    public String getName(); //이지선 or 닉네임(공일공구)
}
