package com.example.bookmanager.service;

import com.example.bookmanager.dto.GetMemberDto;

public interface MemberService {
    GetMemberDto getMember(String userId);
}
