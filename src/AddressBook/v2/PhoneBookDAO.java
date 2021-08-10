package AddressBook.v2;

import java.util.List;

public interface PhoneBookDAO {
	public List<PhoneBookVO> getList();
	public List<PhoneBookVO> search(String keyword);
	public boolean insert(PhoneBookVO vo);
	public boolean delete(Long id);
}
