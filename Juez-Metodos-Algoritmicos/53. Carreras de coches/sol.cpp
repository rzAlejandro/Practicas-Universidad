// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include <algorithm>


// función que resuelve el problema
bool myfunction(std::pair<int,bool> i, std::pair<int, bool> j) { return (i.first > j.first); }

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
void resuelveCaso() {
	// leer los datos de la entrada
	int N, V, v;
	std::cin >> N >> V;
	std::vector<int> v1;
	for (int i = 0; i < N; ++i) {
		std::cin >> v;
		v1.push_back(v);
	}
	std::sort(v1.begin(), v1.end(),std::greater<int>());
	int num = 0;
	int i = 0;
	int jAux = N - 1;
	bool stop = false;
	while (i < N && !stop) {
		int j = jAux;
		bool enc = false;
		while (j > i && !enc) {
			if (v1[i] + v1[j] >= V) {
					enc = true;
					num++;
					jAux = j-1;
			}
		--j;
		}
		if (!enc)
			stop = true;
		++i;
	}
	
	// escribir sol
	std::cout << num << '\n';

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