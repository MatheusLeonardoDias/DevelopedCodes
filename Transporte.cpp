//  g++ Transporte.cpp -o a.o && ./a.o <entrada.txt >output.txt
//  c++11 e g++ 5.4.0;
#include <bits/stdc++.h>

using namespace std;

int n = 1, m = 1, cont = 0;
double Custos[10][10], Solucao[10][10], z = 0, neg = 0;

void print_resultado(){
	cont++;
	cout << "Teste " << cont << endl;
	if( cont > 1 )
		cout.precision(0);
	for( int i = 0; i < m; i++ ){
			for( int j = 0; j < n; j++ ){
				cout << std::noshowpoint << std::setfill (' ') << std::setw (3) << Solucao[i][j] << " ";
				z += Solucao[i][j]*Custos[i][j];
			}
		cout << endl;
	}
	cout.setf(ios::fixed);
	cout.precision(2);
	cout << endl;
	cout << "Z = " << std::setfill (' ') << std::setw (7) << z << std::showpoint << endl;
	z = 0;
	cout << std::noshowpoint << endl;
}

void zera_matriz(){

	for (int i = 0; i < 10; ++i)
	{
		for(int j = 0; j < 10; j++){
			Custos[i][j] = 0;
			Solucao[i][j] = 0;
		}
	}
}

void solucao_inicial( ){
	/* Método Canto Noroeste */
	for( int i = 0; i < m; i++ ){
		Solucao[m-1][i] = Custos[m-1][i];
	}
	for( int i = 0; i < n; i++ ){
		Solucao[i][n-1] = Custos[i][n-1];
	}

	for( int i = 0; i < m-1; i++ ){
		for( int j = 0; j < n-1; j++ ){	
			if( Solucao[i][n-1] >= Solucao[m-1][j] ){
				Solucao[i][j] = Solucao[m-1][j];
				Solucao[m-1][j] = 0;
				Solucao[i][n-1] -= Solucao[i][j];
			}else{
				Solucao[i][j] = Solucao[i][n-1];
				Solucao[i][n-1] = 0;
				Solucao[m-1][j] -= Solucao[i][j];
			}

		}
		
	}
}

void BalanceamentoMatriz( ){
	/* Método Canto Noroeste */

	int Oferta = 0, Demanda = 0, diferenca = 0, dif = 0;
	dif = abs(m - n);
	// cout << m << endl;
	for( int i = 0; i < n; i++ ){
		Oferta += Custos[i][n-1];
	}
	for( int i = 0; i < m; i++ ){
		Demanda += Custos[m-1][i];
	}

	diferenca = abs(Oferta - Demanda);

	int i = 0;
	if( Oferta > Demanda ){
		for (i = 0; i < n; ++i)
		{
			Custos[i][n] = Custos[i][n-1];
			Custos[i][n-1] = 0;
		}
		Custos[m-1][i-1] = diferenca;
		n++;
	}else if( Oferta < Demanda ){
		for (i = 0; i < m; ++i)
		{
			Custos[m][i] = Custos[m-1][i];
			Custos[m-1][i] = 0;
		}
		Custos[i-1][n-1] = diferenca;
		m++;
	}

}

bool VerificaDegeneracao(){
	int cont = 0;
	for (int i = 0; i < m-1; ++i)
	{
		for (int j = 0; j < n-1; ++j)
		{
			if( Solucao[i][j] != 0 )
				cont++;
		}
	}
	if( cont == m + n - 3 )
		return false;
	else
		return true;

}

struct BFSUtil{
	int x, y;
	struct BFSUtil* pai;
};
typedef struct BFSUtil BFStruct;

BFStruct BFS( BFStruct *origem ){
	queue< BFStruct* > Q;
    //Q.push(*origem);
    BFStruct *ProxPosic = (BFStruct*) malloc( sizeof( BFStruct ) );
    ProxPosic = origem;
    /* Testes tratando a primeira iteração */
    do{
    	for (int i = 1; i < m; ++i){
        	
	        if( ProxPosic->x + i < m){
	        	if( ( (ProxPosic->pai != NULL && ( ProxPosic->pai->x != ProxPosic->x+i && ProxPosic->pai->y != ProxPosic->y ) ) || ProxPosic->pai == NULL ) && ( Solucao[ProxPosic->x+i][ProxPosic->y] != 0 || ( ProxPosic->x+i == origem->x && ProxPosic->y == origem->y ) ) ){
	        		pair<int,int> ProxCoord;
	        		ProxCoord.first = ProxPosic->x + i, ProxCoord.second = ProxPosic->y;
	        		BFStruct *Ideia = (BFStruct*) malloc( sizeof( BFStruct ) );
	        		Ideia->x = ProxCoord.first, Ideia->y = ProxCoord.second;
	        		Ideia->pai = ProxPosic;
	        		Q.push(Ideia);
	     	   }
	    	}
	        if( ProxPosic->x - i >= 0 ){
	        	if( ( (ProxPosic->pai != NULL && ( ProxPosic->pai->x != ProxPosic->x-i && ProxPosic->pai->y != ProxPosic->y ) ) || ProxPosic->pai == NULL ) && ( Solucao[ProxPosic->x-i][ProxPosic->y] != 0 || ( ProxPosic->x-i == origem->x && ProxPosic->y == origem->y ) ) ){
	        		pair<int,int> ProxCoord;
	        		ProxCoord.first = ProxPosic->x - i, ProxCoord.second = ProxPosic->y;
		        	BFStruct *Ideia = (BFStruct*) malloc( sizeof( BFStruct ) );
	    	    	Ideia->x = ProxCoord.first, Ideia->y = ProxCoord.second;
	        		Ideia->pai = ProxPosic;
	        		Q.push(Ideia);
	        	}
	        }

	    }
	    for (int i = 1; i < m; ++i){
	        
	        if( ProxPosic->y + i < m ){
	        	if( ( (ProxPosic->pai != NULL && ( ProxPosic->pai->x != ProxPosic->x && ProxPosic->pai->y != ProxPosic->y+i ) ) || ProxPosic->pai == NULL ) && ( Solucao[ProxPosic->x][ProxPosic->y+i] != 0 || ( ProxPosic->x == origem->x && ProxPosic->y+i == origem->y ) ) ){
		        	pair<int,int> ProxCoord;
		        	ProxCoord.first = ProxPosic->x, ProxCoord.second = ProxPosic->y + i;
		        	BFStruct *Ideia = (BFStruct*) malloc( sizeof( BFStruct ) );
		        	Ideia->x = ProxCoord.first, Ideia->y = ProxCoord.second;
		        	Ideia->pai = ProxPosic;
		        	Q.push(Ideia);
	        	}
	        }
	        if( ProxPosic->y - i >= 0 ){
	        	if( ( (ProxPosic->pai != NULL && ( ProxPosic->pai->x != ProxPosic->x && ProxPosic->pai->y != ProxPosic->y-i ) ) || ProxPosic->pai == NULL ) && ( Solucao[ProxPosic->x][ProxPosic->y-i] != 0 || ( ProxPosic->x == origem->x && ProxPosic->y-i == origem->y ) ) ){
		        	pair<int,int> ProxCoord;
		        	ProxCoord.first = ProxPosic->x, ProxCoord.second = ProxPosic->y - i;
		        	BFStruct *Ideia = (BFStruct*) malloc( sizeof( BFStruct ) );
		        	Ideia->x = ProxCoord.first, Ideia->y = ProxCoord.second;
		        	Ideia->pai = ProxPosic;
		        	Q.push(Ideia);
	        	}
	        }
	    }

	    if(!Q.empty()){
	    	ProxPosic = Q.front();
		   	Q.pop();
		}else{
			ProxPosic->x = -1, ProxPosic->y = -1;
			// cout << "oi" << endl;
			return *ProxPosic;
		}

    }while( !( ProxPosic->x == origem->x && ProxPosic->y == origem->y )  );

    return *ProxPosic;
}

// Utilidade para debugar o caminho.
void ImprimeCaminho( BFStruct *elemento, BFStruct *origem ){
	//cout << "yes" << endl;
	cout << elemento->x << " " << elemento->y << " -> ";
	if( elemento->pai != origem ){
		ImprimeCaminho(elemento->pai,origem);
	}else{
		cout << origem->x << " " << origem->y << endl;
	}
}

int CustoCaminho( BFStruct *elemento, BFStruct *origem, int soma, bool op ){
	if(op == true){
		if( Solucao[elemento->x][elemento->y] != -1 )
			soma += Custos[elemento->x][elemento->y];
	}
	else
		if( Solucao[elemento->x][elemento->y] != -1 )
			soma -= Custos[elemento->x][elemento->y];

	if( elemento->pai != NULL && elemento->pai != origem ){
		soma = CustoCaminho(elemento->pai,origem, soma, !op);
	}
	return soma; 
}

bool ExisteCaminhoZeros(){
	BFStruct *ZeroInicial = (BFStruct*) malloc( sizeof( BFStruct ) );
	ZeroInicial->pai = NULL;
	for( int i = 0; i < m-1; i++ ){
		for( int j = 0; j < n-1; j++ ){	
			if( Solucao[i][j] == 0 ){
				ZeroInicial->x = i;
				ZeroInicial->y = j;
				BFStruct *Percurso = (BFStruct*) malloc( sizeof( BFStruct ) );
				*Percurso = BFS( ZeroInicial );
				if( Percurso->x == -1 ){
					Solucao[i][j] = 0;
					return false;
				}
			}
		}
	}
	return true;

}

pair<int,int> EncontraCaminho(){
	BFStruct *ZeroInicial = (BFStruct*) malloc( sizeof( BFStruct ) );
	ZeroInicial->pai = NULL;
	pair<int,int> MenorZ;
	int MenorZi = INT_MAX;
	MenorZ.first = -1, MenorZ.second = -1;
	if( !VerificaDegeneracao()){
		for( int i = 0; i < m-1; i++ ){
			for( int j = 0; j < n-1; j++ ){
				if( Solucao[i][j] == 0 ){
					ZeroInicial->x = i;
					ZeroInicial->y = j;
					BFStruct *Percurso = (BFStruct*) malloc( sizeof( BFStruct ) );
					*Percurso = BFS( ZeroInicial );
					if( Percurso->x == -1 )
						continue;
					int Z = CustoCaminho( Percurso, ZeroInicial, 0, true );
					// ImprimeCaminho( Percurso, ZeroInicial);
					// cout << "Z: " << Z << endl; 
					if( Z < 0 )
						neg = 0;
					if( MenorZi > Z ){
						MenorZi = Z;
						MenorZ.first = ZeroInicial->x, MenorZ.second = ZeroInicial->y;
					}
				}
			}
		}
	}else{ 
		// cout << "Degenerado" << endl;
		/*Encontrar Variavel Artificial*/
		bool QuebraLaco = false;
		for( int i = 0; i < m-1 && !QuebraLaco; i++ ){
			for( int j = 0; j < n-1; j++ ){
				if( Solucao[i][j] == 0 ){
					
					Solucao[i][j] = -1;
					if( !ExisteCaminhoZeros() ){
						// cout << "Nao tem caminho" << endl;
						Solucao[i][j] = 0;
					}else{
						// cout << "QuebraLaco" << endl;
						QuebraLaco = true;
						break;
					}
				}
			}
		}
		/**/

		for( int i = 0; i < m-1; i++ ){
			for( int j = 0; j < n-1; j++ ){
				if( Solucao[i][j] == 0 ){
					ZeroInicial->x = i;
					ZeroInicial->y = j;
					BFStruct *Percurso = (BFStruct*) malloc( sizeof( BFStruct ) );
					*Percurso = BFS( ZeroInicial );
					if( Percurso->x == -1 )
						continue;
					int Z = CustoCaminho( Percurso, ZeroInicial, 0, true );
					// ImprimeCaminho( Percurso, ZeroInicial);
					// cout << "Z: " << Z << endl;
					if( Z < 0 )
						neg = 0;
					if( MenorZi > Z ){
						MenorZi = Z;
						MenorZ.first = ZeroInicial->x, MenorZ.second = ZeroInicial->y;
					}
				}
			}
		}	

	}
	// cout << "ZP: " << MenorZ.first << " " << MenorZ.second << endl;
	return MenorZ;

}

int MenorElementoCaminho( BFStruct *elemento, BFStruct *origem, int menor, bool op ){ // op = false -> usa o valor
	
	if( op == true ){
		if( menor > Solucao[elemento->x][elemento->y] && Solucao[elemento->x][elemento->y] != 0 )
			menor = Solucao[elemento->x][elemento->y];
	}
	
	if( elemento->pai != NULL && elemento->pai != origem ){
		menor = MenorElementoCaminho(elemento->pai, origem, menor, !op );
	}

	return menor; 
}

void RecalculaCaminho( BFStruct *elemento, BFStruct *origem, int menor, bool op ){ // Retorna o valor do caminho feito
	// cout << "Elemento: " << Solucao[elemento->x][elemento->y] << endl;
	if(op == true)
		Solucao[elemento->x][elemento->y] += menor;
	else
		Solucao[elemento->x][elemento->y] -= menor;

	if( elemento->pai != NULL && elemento->pai != origem ){
		RecalculaCaminho(elemento->pai,origem, menor, !op);
	}

}

void TiraMenosUM(){
	for( int i = 0; i < m-1; i++ ){
			for( int j = 0; j < n-1; j++ ){
				if( Solucao[i][j] == -1 )
					Solucao[i][j] = 0;
			}
		}

}

void OperacoesCaminho( pair<int,int> MenorZ ){
	BFStruct *PosInic = (BFStruct*) malloc( sizeof( BFStruct ) );
	PosInic->x = MenorZ.first, PosInic->y = MenorZ.second;

	BFStruct *Percurso = (BFStruct*) malloc( sizeof( BFStruct ) );
	*Percurso = BFS( PosInic );
	//ImprimeCaminho( Percurso, PosInic );
	TiraMenosUM();
	int min = MenorElementoCaminho( Percurso, PosInic, INT_MAX, false );
	// cout << "Menor: " << min << endl;
	RecalculaCaminho( Percurso, PosInic, min, true );
	
}

int main(){

	while( cin >> m >> n && m != 0 && n != 0){
		
		zera_matriz();

		for( int i = 0; i < m; i++ )
			for( int j = 0; j < n; j++ )
				cin >> Custos[i][j];
		BalanceamentoMatriz();
		solucao_inicial();
		do{	
			
			neg = 1;
			pair<int,int> MinElem = EncontraCaminho();
			if( MinElem.first != -1 && MinElem.second != -1 && !neg )
					OperacoesCaminho( MinElem );

		}while( neg == 0 );
		
		
		print_resultado();
	}

	return 0;
}




/* TODO: Calcular degeneração
		 Permitir ao BFS utilizar um zero quando há degeneração
		 Fazer balanceamento das matrizes

*/
