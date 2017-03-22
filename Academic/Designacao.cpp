//  g++ Designacao.cpp -o a.o && ./a.o <entrada.txt >output.txt
//  c++11 e g++ 5.4.0;
#include <bits/stdc++.h>

using namespace std;

int n = 1, m = 1, cont = 0, result = 0;
double D[10][10], B[10][10], z = 0;
bool flag;

void print_resultado(){
	cont++;
	cout << "Teste " << cont << endl;
	for( int i = 0; i < m; i++ ){
			for( int j = 0; j < n; j++ )
				if(D[i][j] == -2){
					cout << "X ";
					z += B[i][j];
				}
				else
					cout << "- ";
		cout << endl;
	}
	cout.setf(ios::fixed);
	cout.setf(ios::showpoint);
	cout.precision(2);
	cout << endl;
	cout << "Z = " << std::setfill (' ') << std::setw (7) << z << endl;
	z = 0;
	cout << endl;
}

int min_linha( int m ){
	int min = 101;

	for( int i = 0; i < n; i++ )
		if(min > D[m][i])
			min = D[m][i];
	return min;
}

int min_coluna( int n ){
	int min = 101;

	for( int i = 0; i < m; i++ )
		if(min > D[i][n])
			min = D[i][n];
	return min; 
}

int verifica_zeros(){
	int vet[10];
	memset(vet, 0, sizeof(vet) );
	for(int j = 0; j < m; j++ )
		for( int i = 0; i < n; i++ )
			if(D[j][i] == 0)
				vet[j]++;

	int minlocal = 0;
	for(int i = 0; i < m; i++ ){
		if( vet[minlocal] == 0 )
			minlocal = i;
		if(vet[minlocal] > vet[i] && vet[i] > 0)
			minlocal = i;
	}
	return minlocal;
}

int ajusta_linha( int m ){
	flag = false;
	int col = 0;
	for( int i = 0; i < n; i++ )
		if( D[m][i] == 0 )
			if( !flag ){
				D[m][i] = -2;
				flag = true;
				col = i;
				result++;
			}
			else
				D[m][i] = -1;
	return col;
}

void ajusta_coluna( int n ){
	
	for( int i = 0; i < m; i++ )
		if( D[i][n] == 0 )
			if( !flag ){
				D[i][n] = -2;
				flag = true;
				result++;
			}
			else
				D[i][n] = -1;
}

bool verifica_duplicidade( list<int> param, int elem ){
	for( list<int>::iterator it = param.begin(); it != param.end(); ++it )
		if( *it == elem )
			return false;

	return true;
}

void ajeita_matriz(){

	if( m > n){
		n = m;
	}
	else if( n > m){
		m = n;
	}
}

void zera_matriz(){

	for (int i = 0; i < 10; ++i)
	{
		for(int j = 0; j < 10; j++){
			D[i][j] = 0;
			B[i][j] = 0;
		}
	}


}

int main(){

	while( cin >> m >> n && m != 0 && n != 0){
		
		zera_matriz();

		for( int i = 0; i < m; i++ )
			for( int j = 0; j < n; j++ )
				cin >> D[i][j], B[i][j] = D[i][j];

			if(m != n)
				ajeita_matriz();

		while(1){

			// Passo 1: encontra o menor elemento de cada linha e subtrair pela linha toda 
			for( int i = 0; i < m; i++ ){
				int min = min_linha(i);
				for( int j = 0; j < n; j++ )
					D[i][j] -= min;
			}

			// Passo 2: encontra o menor elemento de cada coluna e subtrair pela coluna toda 
			for( int i = 0; i < n; i++ ){
				int min = min_coluna(i);
				for( int j = 0; j < m; j++ )
					D[j][i] -= min;
			}
			// Passo 3: Essa funcao retorna o indicie da linha da matrix que possui menor numero de zeros 
			result = 0;
			for(int i = 0; i < m; i++ ){
				int aux = verifica_zeros();
				ajusta_coluna(ajusta_linha(aux));
			}

			if(result == m){
				break;
			}
			// Passo 4: Encontrar a linha sem designacao.
			list<int> lmarcadas, cmarcadas;
			bool desig = false;
			for( int i = 0; i < m; i++ ){
				for( int j = 0; j < n; j++ )
				{
					if( D[i][j] == -2 )
						desig = true;
				}
				if( !desig )
				{
					if( verifica_duplicidade( lmarcadas, i ) )
						lmarcadas.push_back( i );
				}
				desig = false;
			}
			// Passo 5: nas linhas marcadas, marcar as colunas com zero
			for( list<int>::iterator it = lmarcadas.begin(); it != lmarcadas.end(); ++it )
			{
				for( int i = 0; i < n; i++ )
				{
					if( D[*it][i] == -1 )
					{
						if( verifica_duplicidade( cmarcadas, i ) )
							cmarcadas.push_back( i );
					}
				}
			}
			// Passo 6: nas colunas marcadas, marcar as linhas com designacao.
			for( list<int>::iterator it = cmarcadas.begin(); it != cmarcadas.end(); ++it )
			{
				for( int i = 0; i < m; i++ )
				{
					if( D[i][*it] == -2 )
					{
						if( verifica_duplicidade( lmarcadas, i ) )
							lmarcadas.push_back( i );
					}
				}	
			}
			// Passo 7: nas linhas marcadas ,voltar a marcar as colunas com zeros até que não seja possível marcar novas linhas ou colunas
			for( list<int>::iterator it = lmarcadas.begin(); it != lmarcadas.end(); ++it )
			{
				for( int i = 0; i < n; i++ )
				{
					if( D[*it][i] == -1 )
					{
						if( verifica_duplicidade( cmarcadas, i ) )
							cmarcadas.push_back( i );
					}
				}
			}

			// Passo 8: Buscar o menos elemento da matrix das linhas e colunas nao riscadas
			int min = 101;
			for ( int i = 0; i < m; i++ )
			{
				for ( int j = 0; j < n; j++ )
				{	// Pegamos o menor elemento da matriz
					if(D[i][j] < min && !verifica_duplicidade(lmarcadas, i) && verifica_duplicidade(cmarcadas, j)){
						min = D[i][j];
					}
				}
			}
			for ( int i = 0; i < m; i++ )
				for ( int j = 0; j < n; j++ )
					if(D[i][j] < 0 )
						D[i][j] = 0;
			// Passo 9: Subtrair das linhas ou colunas nao marcas
			for ( int i = 0; i < m; i++ )
			{
				for ( int j = 0; j < n; j++ )
				{	
					if(!verifica_duplicidade(lmarcadas, i) && verifica_duplicidade(cmarcadas, j))
						D[i][j] -= min;

					else if(verifica_duplicidade(lmarcadas, i) && !verifica_duplicidade(cmarcadas, j))
						D[i][j] += min;
				}
			}
		}
		print_resultado();
	}
	return 0;
}

// TODO: Arrumar a matriz caso nao seja quadrada
//		 Armazenar a matriz no comeco do programa para calcular o resultado final	
