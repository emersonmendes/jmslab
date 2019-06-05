package br.com.emersonmendes.service;

import br.com.emersonmendes.data.MemberRepository;
import br.com.emersonmendes.model.Member;

import javax.inject.Inject;
import java.util.List;

public class MemberService {

    @Inject
    private MemberRepository memberRepository;

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public void addMember(Member member) {

    }

}
