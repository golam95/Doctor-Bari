package com.doctorbari.serviceImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.doctorbari.dto.PostDto;
import com.doctorbari.helper.CommonMsg;
import com.doctorbari.model.Post;
import com.doctorbari.repository.PostRepository;
import com.doctorbari.service.PostService;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> listOfPost() {
		return postRepository.findAll();
	}

	@Override
	public CommonMsg saveUPost(PostDto postDto) {
		CommonMsg commonMsg = new CommonMsg();
		Post post = new Post();
		if (postDto.getUpdateId().isEmpty()) {
			post.setTitle(postDto.getTitle());
			post.setBody(postDto.getBody());
			post.setDate(new Date());
			commonMsg.setMsgCode("200");
			postRepository.save(post);
		} else {
			post.setPostId(Long.parseLong(postDto.getUpdateId()));
			post.setTitle(postDto.getTitle());
			post.setBody(postDto.getBody());
			post.setDate(new Date());
			commonMsg.setMsgCode("200");
			postRepository.save(post);
		}
		return commonMsg;
	}

	@Override
	public CommonMsg deletePost(Long id) {
		CommonMsg commonMsg = new CommonMsg();
		postRepository.deleteById(id);
		commonMsg.setMsgCode("200");
		return commonMsg;
	}

	@Override
	public Page<Post> findPaginated(Pageable pageable) {
		List<Post> posts = postRepository.findAll();
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Post> list;
		if (posts.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, posts.size());
			list = posts.subList(startItem, toIndex);
		}
		Page<Post> bookPage = new PageImpl<Post>(list, PageRequest.of(currentPage, pageSize), posts.size());
		return bookPage;
	}

	@Override
	public Long totalPost() {
		return postRepository.count();
	}

}
