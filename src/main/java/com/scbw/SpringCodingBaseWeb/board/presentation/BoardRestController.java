package com.scbw.SpringCodingBaseWeb.board.presentation;

import com.scbw.SpringCodingBaseWeb.board.dto.BoardDTO;
import com.scbw.SpringCodingBaseWeb.board.dto.BoardSearchDTO;
import com.scbw.SpringCodingBaseWeb.board.entity.Board;
import com.scbw.SpringCodingBaseWeb.board.service.BoardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class BoardRestController {

    BoardService boardService;

    @PostMapping(path = "/board/api/list")
    public ResponseEntity boardList(BoardSearchDTO search, Pageable pageable) {
        Page<Board> boardPage = boardService.findAll(search, pageable);
        return ResponseEntity.ok(boardPage);
    }

    @GetMapping("/board/api/detail/{boardId}")
    public ResponseEntity board(@PathVariable String boardId) {
        try {
            Board board = boardService.findById(boardId);
            return ResponseEntity.status(HttpStatus.OK).body(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND");
        }
    }

    @PostMapping("/board/api/create")
    @ResponseBody
    public ResponseEntity create(@RequestBody BoardDTO boardDTO) {
        if(boardDTO != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST");
        }

        try {
            Board board = boardService.insert(boardDTO);
            return ResponseEntity.status(HttpStatus.OK).body(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST");
        }
    }

    @PutMapping("/board/api/edit")
    public ResponseEntity edit(@RequestBody BoardDTO boardDTO) {
        try {
            Board board = boardService.update(boardDTO);
            return ResponseEntity.status(HttpStatus.OK).body(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST");
        }
    }

    @DeleteMapping("/board/api/remove/{boardId}")
    public ResponseEntity delete(@PathVariable("boardId") String boardId) {
        try {
            boardService.delete(boardId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND");
        }
    }
}
