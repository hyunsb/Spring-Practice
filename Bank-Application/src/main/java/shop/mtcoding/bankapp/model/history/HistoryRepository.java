package shop.mtcoding.bankapp.model.history;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.bankapp.dto.history.HistoryRespDto;

@Mapper
public interface HistoryRepository {

    List<HistoryRespDto> findByGubun(@Param("gubun") String gubun, @Param("accountId") int accountId);

    int insert(History history);

    int updateById(History history);

    int deleteById(int id);

    List<History> findAll();

    History findById(int id);
}
