package com.example.lunit.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String userName;

    @Column(name = "PASSWORD", nullable = false, length = 1000)
    private String password;

    @Column(length = 320)
    private String email;

    @Builder.Default
    @ColumnDefault("TRUE")
    @Column(name = "IS_ENABLED", nullable = false)
    private Boolean isEnabled = false;

    @Column(name = "MAX_ANALYZE_CNT", nullable = false)
    private Integer maxAnalyzeCnt;

    @Column(name = "CUR_ANALYZE_CNT", nullable = false)
    private Integer curAnalyzeCnt;

    @Column(name = "ROLE", nullable = false, length = 100)
    private String role;

    /**
     * 유효기간(sec)
     */
    @Column(name = "EXPIRE_DURATION")
    private Long expireDuration;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Token> tokens = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<DicomAnalyzeResult> dicomAnalyzeResults = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public boolean isAnalysisAvailable() {
        return this.curAnalyzeCnt < this.maxAnalyzeCnt;
    }
}