package com.doctorbari.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.doctorbari.dto.PostDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Post;

public interface PostService {
	List<Post> listOfPost();
	CommonMsg saveUPost(PostDto postDto);
	CommonMsg deletePost(Long id);
    Page<Post> findPaginated(Pageable pageable) ;
    Long totalPost();
}
