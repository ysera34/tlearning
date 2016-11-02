package com.tacademy.fragment.model;

import com.tacademy.fragment.R;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by yoon on 2016. 11. 1..
 */
public class MemberStorage {

    private static MemberStorage sMemberStorage;

    private HashMap<UUID, Member> mMembers;

    public static MemberStorage get() {
        if (sMemberStorage == null) {
            sMemberStorage = new MemberStorage();
        }
        return sMemberStorage;
    }

    public MemberStorage() {
        mMembers = new HashMap<>();

        // generate member
        Member member;
        member = new Member(R.drawable.girls_generation_hyoyeon, "hyoyeon");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_jesica, "jesica");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_seohyun, "seohyun");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_sunny, "sunny");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_suyoung, "suyoung");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_taeyeon, "taeyeon");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_tifany, "tifany");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_yuna, "yuna");
        mMembers.put(member.getId(), member);
        member = new Member(R.drawable.girls_generation_yuri, "yuri");
        mMembers.put(member.getId(), member);

    }

    public HashMap<UUID, Member> getMembers() {
        return mMembers;
    }

    public Member getMember(UUID id) {
        return mMembers.get(id);
    }
}
