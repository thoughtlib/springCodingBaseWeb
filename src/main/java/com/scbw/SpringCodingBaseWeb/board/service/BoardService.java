package com.scbw.SpringCodingBaseWeb.board.service;

import com.scbw.SpringCodingBaseWeb.board.dto.BoardDTO;
import com.scbw.SpringCodingBaseWeb.board.entity.Board;
import com.scbw.SpringCodingBaseWeb.board.mapper.BoardMapper;
import com.scbw.SpringCodingBaseWeb.board.repository.BoardRepository;
import com.scbw.SpringCodingBaseWeb.util.UUIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Transactional(readOnly = false)
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Optional<Board> findById(String id) {
        return boardRepository.findById(id);
    }

    public Board insert(BoardDTO boardDTO) {
        boardDTO.setBoardId(UUIDGenerator.generate());
        boardDTO.setUsed(true);

        return save(boardDTO);
    }

    public Board update(BoardDTO boardDTO) {
        boardDTO.setUsed(true); // FIXME: 문제가 되는 코드
        return save(boardDTO);
    }

    public Boolean delete(String boardId) {
        try {
            boardRepository.deleteById(boardId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Board save(BoardDTO boardDTO) {
        Board board = boardMapper.dtoToEntity(boardDTO);
        return boardRepository.save(board);
    }
}
