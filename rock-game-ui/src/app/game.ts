import { Player } from './player';

export class Game {

    id: number;
    rounds: number;
    players: Player[];
    gameStats: Map<string, number>;
    lastRoundResult: string;
}
