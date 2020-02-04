import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Game} from './game';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private readonly gameUrl: string;
  private readonly gamesUrl: string;

  private readonly headers: HttpHeaders = new HttpHeaders();

  constructor(private http: HttpClient) {
    this.gameUrl = environment.baseUrl + '/game';
    this.gamesUrl = environment.baseUrl + '/games';
    this.headers = this.headers.set('Content-Type', 'application/json');
  }

  public startGame(playername: string): Observable<Game> {
    return this.http.post<Game>(this.gameUrl + '/new/' + playername, null, { headers: this.headers});
  }

  public playRound(gameId: number): Observable<Game> {
    return this.http.put<Game>(this.gameUrl + '/' + gameId, null, { headers: this.headers});
  }

  public getGame(gameId: number): Observable<Game> {
    return this.http.get<Game>(this.gameUrl + '/' + gameId, { headers: this.headers});
  }

  public getAllGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.gamesUrl + '/all', { headers: this.headers});
  }

  public resetGame(gameId: number): Observable<Game> {
    return this.http.post<Game>(this.gameUrl + '/reset/' + gameId, null, { headers: this.headers});
  }
}
