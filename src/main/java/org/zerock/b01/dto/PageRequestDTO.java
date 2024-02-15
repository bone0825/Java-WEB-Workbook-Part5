package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type; // 검색 종류 t,c,w
    private String keyword;

    //검색 조건 처리
    public String[] getTpyes(){
        if(type == null || type.isEmpty()) return null;
        return type.split("");
    }

    //페이징 처리
    public Pageable getPageable(String...props){
        return (Pageable) PageRequest.of(this.page-1,this.size, Sort.by(props).descending());
    }

    //검색 조건, 페이징 조건 링크 처리
    private String link;

    public String getLink(){
        if(link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page="+this.page);
            builder.append("&size="+this.size);

            if(type != null && type.length() > 0){
                builder.append("&type="+type);
            }

            if(keyword != null){
                try{
                    builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                }
            }
            link = builder.toString();
        }
        return link;
    }
}
