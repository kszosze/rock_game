import { Component, OnInit } from '@angular/core';
import { Player } from '../player';
import { Game } from '../game';
import { GameService } from '../game.service';

@Component({
  selector: 'app-game-play',
  templateUrl: './game-play.component.html',
  styleUrls: ['./game-play.component.css']
})
export class GamePlayComponent implements OnInit {

  player: Player;
  games: Game[];
  gameId: number;
  constructor(private gameService: GameService) { }

  ngOnInit() {
  }

  startGame() {
    this.gameService.startGame(this.player.name).subscribe(game =>
      this.gameId = game.id);
  }

  playGame() {
    this.gameService.playRound(this.gameId).subscribe(game =>
      this.games.push(game)
    );
  }
}
