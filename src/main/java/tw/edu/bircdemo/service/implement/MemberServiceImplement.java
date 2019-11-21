package tw.edu.bircdemo.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.edu.bircdemo.bean.MemberBean;
import tw.edu.bircdemo.dao.MemberDAO;
import tw.edu.bircdemo.dao.implement.MemberDAOImplement;
import tw.edu.bircdemo.service.MemberService;
import tw.edu.bircdemo.vo.Member;

@Service
public class MemberServiceImplement extends BaseServiceImpl<MemberDAO, Member, MemberBean> implements MemberService {
	private MemberDAO memberDAO;

	@Autowired
	public MemberServiceImplement(MemberDAO baseDAO) {
		super(baseDAO);
		this.memberDAO = baseDAO;
	}
	@Transactional
	@Override
	public MemberBean createAndReturn(MemberBean bean) throws RuntimeException {
		Member member = createVO(bean);
		int id = (int) memberDAO.insertAndReturn(member);
		bean.setId(id);
		return bean;
		
	}

	@Override
	protected Member createVO(MemberBean bean) {
		Member member = new Member();
		member.setFirstName(bean.getFirstName());
		member.setLastName(bean.getLastName());
		member.setGender(bean.getGender());
		member.setEmail(bean.getEmail());
		member.setId(bean.getId());
		return member;
	}

	@Override
	protected MemberBean createBean(Member entity) {
		MemberBean memberBean = new MemberBean();
		memberBean.setFirstName(entity.getFirstName());
		memberBean.setLastName(entity.getLastName());
		memberBean.setGender(entity.getGender());
		memberBean.setEmail(entity.getEmail());
		memberBean.setId(entity.getId());
		return memberBean;
	}

}