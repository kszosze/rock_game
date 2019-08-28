import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Game } from './game';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private gamesUrl: string;

  constructor(private http: HttpClient) {
    this.gamesUrl = 'http://localhost:8080/game';
  }

  public startGame(playername: string): Observable<Game> {
    return this.http.post<Game>(this.gamesUrl + '/new/' + playername, null);
  }

  public playRound(gameId: number): Observable<Game> {
    return this.http.put<Game>(this.gamesUrl + '/' + gameId, null);
  }

  public getGame(gameId: number): Observable<Game> {
    return this.http.get<Game>(this.gamesUrl + '/' + gameId);
  }

  public getAllGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.gamesUrl + '/all');
  }

  public resetGame(gameId: number): Observable<Game> {
    return this.http.post<Game>(this.gamesUrl + '/reset/' + gameId, null);
  }
}
