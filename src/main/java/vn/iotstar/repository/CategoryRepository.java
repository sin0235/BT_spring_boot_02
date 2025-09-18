package vn.iotstar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;
import vn.iotstar.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
    @Query("SELECT c FROM Category c ORDER BY c.createdAt DESC")
    List<Category> findAllOrderByCreatedAtDesc();
    
    // Enhanced search methods with pagination
    @Query("SELECT c FROM Category c WHERE LOWER(c.catename) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY c.catename")
    List<Category> findByCatenameContaining(@Param("name") String name);
    
    // Tìm kiếm theo tên category với phân trang
    Page<Category> findByCatenameContainingIgnoreCase(String catename, Pageable pageable);
    
    // Tìm tất cả categories với phân trang
    @NonNull
    Page<Category> findAll(@NonNull Pageable pageable);
    
    // Tìm category theo ID
    Optional<Category> findByCateid(Integer cateid);
    
    // Kiểm tra tên category đã tồn tại
    boolean existsByCatenameIgnoreCase(String catename);
    
    // Kiểm tra tên category đã tồn tại với ID khác (để update)
    @Query("SELECT COUNT(c) > 0 FROM Category c WHERE LOWER(c.catename) = LOWER(:catename) AND c.cateid != :cateid")
    boolean existsByCatenameIgnoreCaseAndCateidNot(@Param("catename") String catename, @Param("cateid") Integer cateid);
    
    // Đếm tổng số categories
    long count();
    
    // Xóa category theo ID
    @Modifying
    @Query("DELETE FROM Category c WHERE c.cateid = :cateid")
    void deleteByCateid(@Param("cateid") Integer cateid);
}