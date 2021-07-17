package mingu.inflearn.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mingu.inflearn.config.exception.BaseException;
import mingu.inflearn.config.http.BaseResponse;
import mingu.inflearn.config.http.BaseResponseCode;
import mingu.inflearn.domain.Board;
import mingu.inflearn.domain.BoardType;
import mingu.inflearn.framework.data.domain.MySQLPageRequest;
import mingu.inflearn.framework.data.domain.PageRequestParameter;
import mingu.inflearn.framework.web.bind.annotation.RequestConfig;
import mingu.inflearn.parameter.BoardParameter;
import mingu.inflearn.parameter.BoardSearchParameter;
import mingu.inflearn.service.BoardService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api(tags = "게시판API")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    @ApiOperation(value = "목록 조회", notes = "게시물 목록 정보를 조회할 수 있습니다.")
    public BaseResponse<List<Board>> getList(@ApiParam BoardSearchParameter parameter,
                                             @ApiParam MySQLPageRequest pageRequest) {
        log.info("pageRequest : {}", pageRequest);
        PageRequestParameter<BoardSearchParameter> pageRequestParameter = new PageRequestParameter<>(pageRequest, parameter);
        return new BaseResponse<>(boardService.getList(pageRequestParameter));
    }

    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
    })
    public BaseResponse<Board> get(@PathVariable int boardSeq) {
        Board board = boardService.get(boardSeq);
        // null 처리
        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
        }
        return new BaseResponse<>(boardService.get(boardSeq));
    }

    @PutMapping
    @RequestConfig
    @ApiOperation(value = "등록 / 수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name = "title", value = "제목", example = "spring"),
            @ApiImplicitParam(name = "contents", value = "내용", example = "spring 강좌"),
    })
    public BaseResponse<Integer> save(BoardParameter parameter) {
        // 제목 필수 체크
        if (ObjectUtils.isEmpty(parameter.getTitle())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "제목" });
        }
        // 내용 필수 체크
        if (ObjectUtils.isEmpty(parameter.getContents())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "내용" });
        }
        boardService.save(parameter);
        return new BaseResponse<>(parameter.getBoardSeq());
    }

    /**
     * 대용량 등록 처리.
     */
    @ApiOperation(value = "대용량 등록처리1", notes = "대용량 등록처리1")
    @PutMapping("/saveList1")
    public BaseResponse<Boolean> saveList1() {
        int count = 0;
        // 테스트를 위한 랜덤 1000건의 데이터를 생성
        List<BoardParameter> list = new ArrayList<>();
        while (true) {
            count++;
            BoardType boardType = BoardType.FAQ;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(boardType, title, contents));
            if (count >= 10000) {
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList1(list);
        long end = System.currentTimeMillis();
        log.info("실행 시간 : {}", (end - start) / 1000.0);
        return new BaseResponse<>(true);
    }

    /**
     * 대용량 등록 처리.
     */
    @PutMapping("/saveList2")
    @ApiOperation(value = "대용량 등록처리2", notes = "대용량 등록처리2")
    public BaseResponse<Boolean> saveList2() {
        int count = 0;
        // 테스트를 위한 랜덤 1000건의 데이터를 생성
        List<BoardParameter> list = new ArrayList<BoardParameter>();
        while (true) {
            count++;
            BoardType boardType = BoardType.INQUIRY;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(boardType, title, contents));
            if (count >= 10000) {
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList2(list);
        long end = System.currentTimeMillis();
        log.info("실행 시간 : {}", (end - start) / 1000.0);
        return new BaseResponse<>(true);
    }

    @DeleteMapping("/{boardSeq}")
    @RequestConfig
    @ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
    })
    public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
        Board board = boardService.get(boardSeq);
        if (board == null) {
            return new BaseResponse<>(false);
        }
        boardService.delete(boardSeq);
        return new BaseResponse<>(true);
    }


}
