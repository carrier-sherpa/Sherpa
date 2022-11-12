package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.Member;
import com.sherpa.carrier_sherpa.domain.enums.MemberRole;
import com.sherpa.carrier_sherpa.domain.repository.MemberRepository;
import com.sherpa.carrier_sherpa.dto.Member.MemberCreateReqDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberFormDto;
import com.sherpa.carrier_sherpa.dto.Member.MemberResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberResDto findByEmail(String email){
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        return new MemberResDto(
                findMember.get().getId(),
                findMember.get().getEmail());
    }

    public MemberResDto signUp(MemberCreateReqDto memberCreateReqDto) {
        System.out.println("memberCreateReqDto = " + memberCreateReqDto.getEmail());
        Optional<Member> findMember = memberRepository.findByEmail(memberCreateReqDto.getEmail());
        if (findMember.isPresent()) {
            throw new IllegalStateException("존재하는 회원입니다.");
        }

        Member createMember = Member.builder()
                .email(memberCreateReqDto.getEmail())
                .password(bCryptPasswordEncoder.encode(memberCreateReqDto.getPassword()))
                .role(MemberRole.USER)
                .build();

        memberRepository.save(createMember);

        return new MemberResDto(
                createMember.getId(),
                createMember.getEmail());
    }

    public MemberResDto signIn(MemberFormDto memberformDto) {
        Optional<Member> loginMember = memberRepository.findByEmail(memberformDto.getEmail());
        if (loginMember == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
        System.out.println("memberformDto.getPassword() = " + bCryptPasswordEncoder.encode(memberformDto.getPassword()));
        System.out.println("loginMember.getPassword = " + loginMember.get().getPassword());
        if (!bCryptPasswordEncoder.matches(memberformDto.getPassword(),loginMember.get().getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return new MemberResDto(
                loginMember.get().getId(),
                loginMember.get().getEmail());
    }

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember.get() != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
//        Member member = new Member();

        return Member.builder()
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .role(MemberRole.USER)
                .build();
    }

    private MemberFormDto createMemberFormDto(Member member) {
        return new MemberFormDto(member.getEmail(), member.getPassword());
    }

    public MemberFormDto findMember(MemberFormDto memberFormDto) {
        Optional<Member> findMember = memberRepository.findByEmail(memberFormDto.getEmail());
        return createMemberFormDto(findMember.get());
    }

}
