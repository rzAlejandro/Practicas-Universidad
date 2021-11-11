#ifndef HORAS_H
#define HORAS_H

#include <iostream>
#include <iomanip>
#include <fstream>
#include <iostream>
#include <vector>
#include <stdexcept> // std::domain_error

class hora {
private:
	int _h;
	int _m;

public:
	int minutos() {
		return _m +_h * 60;
	}
	hora(int h = 0, int m = 0) throw (std::invalid_argument) {
		if (0 <= h && h < 24 && 0 <= m && m < 60) {
			_h = h;
			_m = m;	
		}
		else throw std::invalid_argument("La hora no es correcta");
	}
	
	bool operator < (hora const & h) const {
		return 60 * _h +   _m<=
			60 * h._h +  h._m;

	}
	void print(std::ostream & o = std::cout) const {
		o << std::setfill('0') << std::setw(2) << _h << ':';
		o << std::setfill('0') << std::setw(2) << _m << ':';
	
	}

	void read(std::istream & e = std::cin) {
		int h, m;
		char c;
		e >> h >> c >> m;
		*this = hora(h, m);
	}
};

inline std::ostream & operator<< (std::ostream & salida, hora const& hora) {
	hora.print(salida);
	return salida;
}
inline std::istream & operator>> (std::istream & entrada, hora & hora) {
	hora.read(entrada);
	return entrada;

}

#endif HORAS_H
