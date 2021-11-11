// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include "Grafo.h"
#include "queue_eda.h"

const std::vector<int> incF = {-1,0,1,0};
const std::vector<int> incC = {0,1,0,-1};

enum tColor { WHITE, GREY, BLACK };


bool existe(int alto,int ancho,int i,int j) {
	return 0 <= i && i < alto && 0 <= j && j < ancho;
}
bool noEsSensor(char c) {
	return !(c >= '0' && c <= '9');
}

void marcar(std::vector<std::vector<std::pair<char, bool>>> & matriz, std::pair<int, std::pair<int, int>> const& posSensor,int alto,int ancho) {
	int k = posSensor.first;
	std::pair<int, int> pos = posSensor.second;
	for (int i = 0; i < 4; ++i) {
		int j = 0;
		bool imposibleAvanzar = false;
		while (j <= k && !imposibleAvanzar) {
			int filaP = pos.first + incF[i] * j;
			int colP = pos.second + incC[i] * j;
			if (existe(alto, ancho, filaP, colP)) { // Podemos alcanzar la casilla
				if (matriz[filaP][colP].first == '#') // Si es muro no prosigue el sensor en esa direccion. Los muros ya los tenía marcados
					imposibleAvanzar = true;
				else {
					if (noEsSensor(matriz[filaP][colP].first))// Si existe, no es muro ni sensor, marcar como false.
						matriz[filaP][colP].second = false;
				}
			}
			else {
				imposibleAvanzar = true;
			}
			++j;
		}

	}
}

bool bfs(int ver, Grafo grafo, std::vector<tColor> & marcas, int fin,int & sol, std::vector<bool> const & estadoCasillas) {
	marcas[ver] = GREY;
	std::vector<int> niveles(grafo.V(),0);
	queue <int> q;
	if (!estadoCasillas[ver]) {
		return false;
	}
	q.push(ver);
	bool enc = false;
	while (!q.empty() && !enc) {
		int x = q.front();
		q.pop();
		std::vector<int> ad = grafo.ady(x);
		int i = 0;
		while (i < ad.size() && !enc) {
			int k = ad[i];
			if (marcas[k] == WHITE && estadoCasillas[k]) {
				niveles[k] = niveles[x] + 1;
				if (k == fin)
					enc = true;
				else {
					marcas[k] = GREY;
					q.push(k);
				}
				
			}
			++i;
		}
		
	}
	sol = niveles[fin];
	return enc;
}

// función que resuelve el problema
bool resolver(Grafo const & grafo, int origen, int fin, int & sol,std::vector<bool> const & estadoCasillas) {
	//Mediante busqueda en profundidad con poda, llegamos si existe al camino mas corto.
	int n = grafo.V();
	std::vector<int> niveles(n);
	std::vector<tColor> marcas(n, WHITE);
	return bfs(origen, grafo, marcas, fin, sol,estadoCasillas);
}

Grafo construirGrafo(std::vector<std::vector<std::pair<char, bool>>> & matriz, std::vector<std::pair<int, std::pair<int, int>>>  const & posSensores,std::vector<bool> & estadoCasillas) {
	int alto = matriz.size();
	int ancho = matriz[0].size();
	for (int i = 0; i < posSensores.size(); ++i) {
		marcar(matriz, posSensores[i],alto,ancho);
	}
	Grafo grafo (alto*ancho);
	for (int i = 0; i < alto; ++i) {
		for (int j = 0; j < ancho; ++j) {
			estadoCasillas[ancho*i + j] = matriz[i][j].second;
			if (existe(alto, ancho, i, j + 1))
				grafo.ponArista(ancho * i + j, ancho * i + j + 1);
				
			if(existe(alto, ancho, i +1, j))
				grafo.ponArista( ancho * i + j,  ancho * (i+1) + j);
		}
	}
	return grafo;
	
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
void resuelveCaso() {
	// leer los datos de la entrada
	char c;
	int alto, ancho, origen, fin, num, sol = 0;
	std::cin >> ancho >> alto;
	std::vector<std::vector<std::pair<char,bool>>> matriz (alto);
	std::vector<std::pair<int,std::pair<int, int>>> posSensores;

	for(int i=0;i<alto;++i){

		for (int j = 0; j < ancho; ++j) {
			std::cin >> c;
			if (c >= '0' && c <= '9') {
				num = int(c)-'0';
				posSensores.push_back({ num,{ i,j } });
				matriz[i].push_back({ c,false });
			}
			else {
				if (c == '#') 
					matriz[i].push_back({ c,false });
				else {
					if (c == 'E')
						origen = ancho*i + j;
					if (c == 'P')
						fin = ancho*i + j;

					matriz[i].push_back({ c,true });
				}
			}

		}
	}
	std::vector<bool> estadoCasillas(alto*ancho);
	Grafo grafo = construirGrafo(matriz,posSensores, estadoCasillas);
	bool ok = resolver(grafo,origen,fin,sol, estadoCasillas);
	// escribir sol
	if (ok)
		std::cout << sol << '\n';
	else std::cout << "NO" << '\n';


}

int main() {
	// Para la entrada por fichero.
	// Comentar para acepta el reto
#ifndef DOMJUDGE
     std::ifstream in("datos.txt");
     auto cinbuf = std::cin.rdbuf(in.rdbuf()); //save old buf and redirect std::cin to casos.txt
#endif 


	int numCasos;
	std::cin >> numCasos;
	for (int i = 0; i < numCasos; ++i)
		resuelveCaso();


	// Para restablecer entrada. Comentar para acepta el reto
#ifndef DOMJUDGE // para dejar todo como estaba al principio
    std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}