package hello.hellowspring.service;

import hello.hellowspring.domain.Member;
import hello.hellowspring.repository.MemberRepository;
import hello.hellowspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     *
     *
     * 회원가입
     *
     */

    public Long join (Member member) {
        validateDuplicateMamber(member); //중복회원 검증
        memberRepository.save(member);
        return  member.getId();

    }

    private void validateDuplicateMamber(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
             throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /**
     *
     * 전체 회원조회
     */


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

        public Optional<Member> findOne(Long memberId) {
        return  memberRepository.findById(memberId);
        }
}
