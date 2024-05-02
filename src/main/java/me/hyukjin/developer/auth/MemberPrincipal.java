package me.hyukjin.developer.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import me.hyukjin.developer.member.payload.MemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Schema(description="CommonPrincipal 생성")
public class MemberPrincipal {

    //principal 생성
    public static CommonPrincipal create(MemberDto.Member member) {
        String roleNamePrefix = "ROLE_";

        return CommonPrincipal.builder()
                .email((String)member.getEmail())
                .name((String)member.getName())
                .pwd(member.getPwd())
                .authorities(Arrays.<GrantedAuthority>asList(new SimpleGrantedAuthority(roleNamePrefix.concat("USER"))))
                .build();
    }

    //프론트 노출시 필요한 데이터 추출할 것
    public static Map<String, String> makeDetail(Map<String,Object> member, HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<String, String>();

        if(request == null) {
            try {
                ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                request = sra.getRequest();
            } catch (Exception e) {
            }
        }
        return resultMap;
    }

}
