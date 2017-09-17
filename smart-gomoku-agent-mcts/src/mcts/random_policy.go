package mcts

import (
	"math"
	"math/rand"

	"gomoku"
)

type RandomMonteCarloTreePolicy struct {
	Range int
}

func (p *RandomMonteCarloTreePolicy) Simulate(board gomoku.Board, chessType gomoku.ChessType) (numOfWin int, numOfGame int, winner gomoku.ChessType) {
	locations := p.Around(&board)
	referee := gomoku.GetReferee()
	for len(locations) > 0 {
		// choose a location
		nextIndex := rand.Intn(len(locations))
		loc := locations[nextIndex]
		// remove the location chosed
		locations = append(locations[:nextIndex], locations[nextIndex+1:]...)
		// make a move
		if (&board).GetByLocation(loc) == gomoku.Empty {
			(&board).SetChessType(loc, chessType)
			// game over
			if referee.IsWin(&board, loc) {
				return 1, 1, chessType
			}
			// game on
			chessType = referee.NextType(chessType)
			locations = append(locations, p.AroundLocation(&board, loc)...)
		}
	}
	return 0, 1, gomoku.Empty
}

func (p *RandomMonteCarloTreePolicy) Evaluate(root *MonteCarloTreeNode, childIndex int) float64 {
	child := root.childrenNode[childIndex]
	exploitation := float64(child.numOfWin) / float64(1+child.numOfGame)
	exploration := math.Sqrt(math.Log2(float64(1+root.numOfGame)) * 2 / float64(1+child.numOfGame))
	return exploitation + exploration
}

func (p *RandomMonteCarloTreePolicy) AroundLocation(board *gomoku.Board, loc gomoku.Location) (locs []gomoku.Location) {
	rowStart := loc.X - p.Range
	if rowStart < 0 {
		rowStart = 0
	}
	for i := rowStart; i < board.Row && i <= loc.X+p.Range; i++ {
		columnStart := loc.Y - p.Range
		if columnStart < 0 {
			columnStart = 0
		}
		for j := columnStart; j < board.Column && j <= loc.Y+p.Range; j++ {
			if board.Get(i, j) == gomoku.Empty {
				locs = append(locs, gomoku.Location{X: i, Y: j})
			}
		}
	}
	return
}

func (p *RandomMonteCarloTreePolicy) Around(board *gomoku.Board) (locs []gomoku.Location) {
	visited := make(map[gomoku.Location]bool)
	for i := 0; i < board.Row; i++ {
		for j := 0; j < board.Column; j++ {
			if board.Get(i, j) == gomoku.Empty {
				continue
			}
			around := p.AroundLocation(board, gomoku.Location{X: i, Y: j})
			for k := range around {
				_, ok := visited[around[k]]
				if !ok {
					locs = append(locs, around[k])
					visited[around[k]] = true
				}
			}
		}
	}
	if len(locs) == 0 {
		locs = []gomoku.Location{{X: board.Row / 2, Y: board.Column / 2}}
	}
	return
}