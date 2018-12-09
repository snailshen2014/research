package dao;

import java.util.List;
import com.elec.domain.ElecRecord;

public interface ElecRecordDao {
	public List<ElecRecord> getRecords();
	public void save(ElecRecord elec);
	public void delete(Integer begin,Integer end);
	public void update(Integer begin,Integer end);
}
