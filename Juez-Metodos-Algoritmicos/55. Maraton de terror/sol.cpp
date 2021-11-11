// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include "horas.h"
#include <algorithm>

// función que resuelve el problema
bool myfunction(std::pair<int, int> p1, std::pair<int, int> p2) {
	return p1.first < p2.first;
}
int convertirHora(hora h) {
	return h.minutos();
}
bool perteneceInt(int x, std::pair<int, int> inter) {
	return x >= inter.first && x < inter.second;
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N, duracion;
	std::cin >> N;
	if (N==0)
		return false;
	hora h;
	std::vector<std::pair<int, int>> pelis;
	for (int i = 0; i < N; ++i) {
		std::cin >> h >> duracion;
		int m = convertirHora(h);
		pelis.push_back({m, m + duracion });
	}
	std::sort(pelis.begin(), pelis.end(), myfunction);
	int sol = 1;
	std::vector<std::pair<int, int>> intervalos;
	intervalos.push_back(pelis[0]);
	for (int i = 1; i < pelis.size(); ++i) {
		if (perteneceInt(pelis[i].first, intervalos[intervalos.size() - 1])) {
			if (pelis[i].second < intervalos[intervalos.size() - 1].second) {
				intervalos[intervalos.size() - 1] = pelis[i];
			}
		}
		else {
			if (pelis[i].first >= intervalos[intervalos.size() - 1].second + 10) {
				sol++;
				intervalos.push_back(pelis[i]);
			}
		}
	}
	// escribir sol
	std::cout << sol << '\n';
	return true;

}

int main() {
	// Para la entrada por fichero.
	// Comentar para acepta el reto
#ifndef DOMJUDGE
 std::ifstream in("datos.txt");
 auto cinbuf = std::cin.rdbuf(in.rdbuf()); //save old buf and redirect std::cin to casos.txt
#endif 


	while (resuelveCaso())
		;


	// Para restablecer entrada. Comentar para acepta el reto
#ifndef DOMJUDGE // para dejar todo como estaba al principio
std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}