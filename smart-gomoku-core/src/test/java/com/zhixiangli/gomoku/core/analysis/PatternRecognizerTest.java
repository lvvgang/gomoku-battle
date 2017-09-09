/**
 * 
 */
package com.zhixiangli.gomoku.core.analysis;

import static com.zhixiangli.gomoku.core.chessboard.ChessType.BLACK;
import static com.zhixiangli.gomoku.core.chessboard.ChessType.EMPTY;
import static com.zhixiangli.gomoku.core.chessboard.ChessType.WHITE;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.zhixiangli.gomoku.core.chessboard.ChessPatternType;

/**
 * @author zhixiangli
 *
 */
public class PatternRecognizerTest {

    @Test
    public void getChessPatternType() {
        Assert.assertEquals(ChessPatternType.FIVE,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, BLACK, BLACK, BLACK, BLACK, BLACK), BLACK));

        Assert.assertEquals(ChessPatternType.OPEN_FOUR,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, WHITE, WHITE, WHITE, WHITE, EMPTY), WHITE));

        Assert.assertEquals(ChessPatternType.HALF_OPEN_FOUR,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, BLACK, BLACK, BLACK, BLACK, WHITE), BLACK));

        Assert.assertEquals(ChessPatternType.OPEN_THREE,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, BLACK, BLACK, BLACK, EMPTY, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.SPACED_OPEN_THREE,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, BLACK, BLACK, EMPTY, BLACK, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.HALF_OPEN_THREE,
                PatternRecognizer.getPatternType(Arrays.asList(WHITE, BLACK, BLACK, BLACK, EMPTY, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.OPEN_TWO,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, BLACK, BLACK, EMPTY, EMPTY, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.ONE_SPACED_OPEN_TWO,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, EMPTY, BLACK, EMPTY, BLACK, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.TWO_SPACED_OPEN_TWO,
                PatternRecognizer.getPatternType(Arrays.asList(EMPTY, BLACK, EMPTY, EMPTY, BLACK, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.HALF_OPEN_TWO,
                PatternRecognizer.getPatternType(Arrays.asList(WHITE, BLACK, EMPTY, BLACK, EMPTY, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.OTHERS,
                PatternRecognizer.getPatternType(Arrays.asList(BLACK, WHITE, BLACK, WHITE, BLACK, EMPTY), BLACK));

    }

    @Test
    public void getBestPatternType() {
        Assert.assertEquals(ChessPatternType.FIVE, PatternRecognizer
                .getBestPatternType(Arrays.asList(EMPTY, WHITE, WHITE, BLACK, BLACK, BLACK, BLACK, BLACK), BLACK));

        Assert.assertEquals(ChessPatternType.SPACED_OPEN_THREE, PatternRecognizer.getBestPatternType(
                Arrays.asList(EMPTY, WHITE, EMPTY, BLACK, BLACK, EMPTY, BLACK, EMPTY, WHITE, EMPTY), BLACK));

        Assert.assertEquals(ChessPatternType.HALF_OPEN_TWO, PatternRecognizer
                .getBestPatternType(Arrays.asList(WHITE, BLACK, EMPTY, BLACK, EMPTY, EMPTY, WHITE, BLACK), BLACK));
    }

}