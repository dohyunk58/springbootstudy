package so.dohyunk58.springbootstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired // MemberRepository 빈 주입
    MemberRepository MemberRepository;

    public List<Member> getAllMembers() {
        return MemberRepository.findAll();
    }
}
