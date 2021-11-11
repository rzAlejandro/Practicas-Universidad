package alex;
import errors.GestionErroresTiny;


public class AnalizadorLexicoTiny implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private ALexOperations ops;
  private int charline = 0;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yychar +1 - charline;}
  private void refrescaColumna() {
    charline = yychar + yytext().length();
  }
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoTiny (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public AnalizadorLexicoTiny (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoTiny () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NOT_ACCEPT,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"54:8,2:2,1,54:2,1,54:18,2,13,23,54:2,48,49,8,17,18,7,46,9,47,10,3,6,5:9,12," +
"11,53,51,52,14,54,4:12,24,4:5,44,4:7,21,54,22,54:3,25,41,38,34,31,32,45,29," +
"26,4:2,30,40,27,42,35,4,36,33,37,39,43,28,4:3,19,50,20,54:65,16,54:2,15,54:" +
"65341,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,128,
"0,1:3,2,3,4,1,5,1:4,6,1:6,7,8,1,9,10,11,1:2,3,1:9,3:18,12,13,1:2,14,15,16,1" +
"7,18,19,20,21,22,16,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,4" +
"1,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,6" +
"6,67,68,69,70,71,72,73,74,75,76,77,78,79,80")[0];

	private int yy_nxt[][] = unpackFromString(81,55,
"1,2,3,4,5,6,58,7,8,9,10,11,12,13,59:3,14,15,16,17,18,19,62,103,5,57,5,116,5" +
":2,87,117,124,5,118,125,104,105,5:2,106,5,107,126,5,20,21,22,65,68,23,24,25" +
",59,-1:58,56,-1:3,60,-1:51,5:3,-1:17,5:22,-1:14,6:2,-1:50,63,-1,63:3,-1:2,6" +
"3:6,66,-1,63:6,-1,63:22,-1:60,26,-1:49,29,-1:55,30,-1:58,33,-1:54,34,-1:54," +
"35,-1:4,36,56,-1,56:3,-1:17,56:22,-1:13,5:3,-1:17,5:3,61,5:4,28,5:13,-1:10," +
"60:2,-1,60:3,73,-1:16,60:22,-1:13,5:3,-1:17,5:13,38,5:8,-1:11,69,-1,69:3,-1" +
":2,69:6,71,-1,69:6,27,69:22,-1:17,37,-1:50,5:3,-1:17,5:10,39,5:4,70,5:6,-1:" +
"58,31,-1:21,63,-1:42,5:3,-1:17,5:3,40,5:18,-1:59,32,-1:8,5:3,-1:17,5:16,41," +
"5:5,-1:25,69,-1:42,5:3,-1:17,5:7,42,5:14,-1:12,36,-1:55,5:3,-1:17,5:7,43,5:" +
"14,-1:13,5:3,-1:17,5:7,44,5:14,-1:13,5:3,-1:17,5:12,45,5:9,-1:13,5:3,-1:17," +
"5:6,46,5:15,-1:13,5:3,-1:17,5:10,47,5:11,-1:13,5:3,-1:17,5:7,48,5:14,-1:13," +
"5:3,-1:17,5:7,49,5:14,-1:13,5:3,-1:17,5:13,50,5:8,-1:13,5:3,-1:17,5:5,51,5:" +
"16,-1:13,5:3,-1:17,5:13,52,5:8,-1:13,5:3,-1:17,5:3,53,5:18,-1:13,5:3,-1:17," +
"5:21,54,-1:13,5:3,-1:17,5:18,55,5:3,-1:13,5:3,-1:17,5:3,64,5:2,89,5:15,-1:1" +
"3,5:3,-1:17,5:2,67,5:19,-1:13,5:3,-1:17,5:9,72,5:12,-1:13,5:3,-1:17,5:15,74" +
",5:6,-1:13,5:3,-1:17,5:9,75,5:12,-1:13,5:3,-1:17,5,76,5:20,-1:13,5:3,-1:17," +
"5:18,77,5:3,-1:13,5:3,-1:17,5:2,78,5:19,-1:13,5:3,-1:17,5:6,79,5:15,-1:13,5" +
":3,-1:17,5:9,80,5:12,-1:13,5:3,-1:17,5:3,81,5:18,-1:13,5:3,-1:17,5:14,82,5:" +
"7,-1:13,5:3,-1:17,5:14,83,5:7,-1:13,5:3,-1:17,5:12,84,5:9,-1:13,5:3,-1:17,5" +
":3,85,5:18,-1:13,5:3,-1:17,5:6,86,5:15,-1:13,5:3,-1:17,5,88,5:20,-1:13,5:3," +
"-1:17,5:12,90,5:9,-1:13,5:3,-1:17,5,91,5:3,92,5:16,-1:13,5:3,-1:17,5,127,5:" +
"16,93,5:3,-1:13,5:3,-1:17,5:18,94,5:3,-1:13,5:3,-1:17,5:2,95,5:19,-1:13,5:3" +
",-1:17,5:6,96,5:15,-1:13,5:3,-1:17,5:2,97,5:19,-1:13,5:3,-1:17,5:13,98,5:8," +
"-1:13,5:3,-1:17,5:15,99,5:6,-1:13,5:3,-1:17,5:15,100,5:6,-1:13,5:3,-1:17,5:" +
"2,101,5:19,-1:13,5:3,-1:17,5:18,102,5:3,-1:13,5:3,-1:17,5:5,108,5:16,-1:13," +
"5:3,-1:17,5,109,5:20,-1:13,5:3,-1:17,5:12,110,5:9,-1:13,5:3,-1:17,5:2,111,5" +
":19,-1:13,5:3,-1:17,5:12,112,5:9,-1:13,5:3,-1:17,5:13,113,5:8,-1:13,5:3,-1:" +
"17,5:12,114,5:9,-1:13,5:3,-1:17,5:13,115,5:8,-1:13,5:3,-1:17,5:4,119,5:8,12" +
"0,5:8,-1:13,5:3,-1:17,5:7,121,5:14,-1:13,5:3,-1:17,5:13,122,5:8,-1:13,5:3,-" +
"1:17,5:12,123,5:9,-1:9");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{refrescaColumna();}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return ops.unidadDiv();}
					case -5:
						break;
					case 5:
						{return ops.unidadId();}
					case -6:
						break;
					case 6:
						{return ops.unidadEnt();}
					case -7:
						break;
					case 7:
						{return ops.unidadMul();}
					case -8:
						break;
					case 8:
						{errores.errorLexico(fila(),lexema());}
					case -9:
						break;
					case 9:
						{return ops.unidadComa();}
					case -10:
						break;
					case 10:
						{return ops.unidadPunto();}
					case -11:
						break;
					case 11:
						{return ops.unidadPuntoYComa();}
					case -12:
						break;
					case 12:
						{return ops.unidadDosPuntos();}
					case -13:
						break;
					case 13:
						{return ops.unidadNot();}
					case -14:
						break;
					case 14:
						{return ops.unidadParentesisApertura();}
					case -15:
						break;
					case 15:
						{return ops.unidadParentesisCierre();}
					case -16:
						break;
					case 16:
						{return ops.unidadLlaveApertura();}
					case -17:
						break;
					case 17:
						{return ops.unidadLlaveCierre();}
					case -18:
						break;
					case 18:
						{return ops.unidadCorcheteApertura();}
					case -19:
						break;
					case 19:
						{return ops.unidadCorcheteCierre();}
					case -20:
						break;
					case 20:
						{return ops.unidadSuma();}
					case -21:
						break;
					case 21:
						{return ops.unidadResta();}
					case -22:
						break;
					case 22:
						{return ops.unidadModulo();}
					case -23:
						break;
					case 23:
						{return ops.unidadIgual();}
					case -24:
						break;
					case 24:
						{return ops.unidadMayor();}
					case -25:
						break;
					case 25:
						{return ops.unidadMenor();}
					case -26:
						break;
					case 26:
						{return ops.unidadDesigual();}
					case -27:
						break;
					case 27:
						{return ops.unidadCadena();}
					case -28:
						break;
					case 28:
						{return ops.unidadIf();}
					case -29:
						break;
					case 29:
						{return ops.unidadMasMas();}
					case -30:
						break;
					case 30:
						{return ops.unidadMenosMenos();}
					case -31:
						break;
					case 31:
						{return ops.unidadAnd();}
					case -32:
						break;
					case 32:
						{return ops.unidadOr();}
					case -33:
						break;
					case 33:
						{return ops.unidadIgualIgual();}
					case -34:
						break;
					case 34:
						{return ops.unidadMayorIgual();}
					case -35:
						break;
					case 35:
						{return ops.unidadMenorIgual();}
					case -36:
						break;
					case 36:
						{refrescaColumna();}
					case -37:
						break;
					case 37:
						{return ops.unidadCaracter();}
					case -38:
						break;
					case 38:
						{return ops.unidadInt();}
					case -39:
						break;
					case 39:
						{return ops.unidadEnd();}
					case -40:
						break;
					case 40:
						{return ops.unidadMain();}
					case -41:
						break;
					case 41:
						{return ops.unidadEnum();}
					case -42:
						break;
					case 42:
						{return ops.unidadElse();}
					case -43:
						break;
					case 43:
						{return ops.unidadTrue();}
					case -44:
						break;
					case 44:
						{return ops.unidadCase();}
					case -45:
						break;
					case 45:
						{return ops.unidadChar();}
					case -46:
						break;
					case 46:
						{return ops.unidadBool();}
					case -47:
						break;
					case 47:
						{return ops.unidadVoid();}
					case -48:
						break;
					case 48:
						{return ops.unidadWhile();}
					case -49:
						break;
					case 49:
						{return ops.unidadFalse();}
					case -50:
						break;
					case 50:
						{return ops.unidadPrint();}
					case -51:
						break;
					case 51:
						{return ops.unidadSwitch();}
					case -52:
						break;
					case 52:
						{return ops.unidadStruct();}
					case -53:
						break;
					case 53:
						{return ops.unidadReturn();}
					case -54:
						break;
					case 54:
						{return ops.unidadString();}
					case -55:
						break;
					case 55:
						{return ops.unidadBartolo();}
					case -56:
						break;
					case 57:
						{return ops.unidadId();}
					case -57:
						break;
					case 58:
						{return ops.unidadEnt();}
					case -58:
						break;
					case 59:
						{errores.errorLexico(fila(),lexema());}
					case -59:
						break;
					case 61:
						{return ops.unidadId();}
					case -60:
						break;
					case 62:
						{errores.errorLexico(fila(),lexema());}
					case -61:
						break;
					case 64:
						{return ops.unidadId();}
					case -62:
						break;
					case 65:
						{errores.errorLexico(fila(),lexema());}
					case -63:
						break;
					case 67:
						{return ops.unidadId();}
					case -64:
						break;
					case 68:
						{errores.errorLexico(fila(),lexema());}
					case -65:
						break;
					case 70:
						{return ops.unidadId();}
					case -66:
						break;
					case 72:
						{return ops.unidadId();}
					case -67:
						break;
					case 74:
						{return ops.unidadId();}
					case -68:
						break;
					case 75:
						{return ops.unidadId();}
					case -69:
						break;
					case 76:
						{return ops.unidadId();}
					case -70:
						break;
					case 77:
						{return ops.unidadId();}
					case -71:
						break;
					case 78:
						{return ops.unidadId();}
					case -72:
						break;
					case 79:
						{return ops.unidadId();}
					case -73:
						break;
					case 80:
						{return ops.unidadId();}
					case -74:
						break;
					case 81:
						{return ops.unidadId();}
					case -75:
						break;
					case 82:
						{return ops.unidadId();}
					case -76:
						break;
					case 83:
						{return ops.unidadId();}
					case -77:
						break;
					case 84:
						{return ops.unidadId();}
					case -78:
						break;
					case 85:
						{return ops.unidadId();}
					case -79:
						break;
					case 86:
						{return ops.unidadId();}
					case -80:
						break;
					case 87:
						{return ops.unidadId();}
					case -81:
						break;
					case 88:
						{return ops.unidadId();}
					case -82:
						break;
					case 89:
						{return ops.unidadId();}
					case -83:
						break;
					case 90:
						{return ops.unidadId();}
					case -84:
						break;
					case 91:
						{return ops.unidadId();}
					case -85:
						break;
					case 92:
						{return ops.unidadId();}
					case -86:
						break;
					case 93:
						{return ops.unidadId();}
					case -87:
						break;
					case 94:
						{return ops.unidadId();}
					case -88:
						break;
					case 95:
						{return ops.unidadId();}
					case -89:
						break;
					case 96:
						{return ops.unidadId();}
					case -90:
						break;
					case 97:
						{return ops.unidadId();}
					case -91:
						break;
					case 98:
						{return ops.unidadId();}
					case -92:
						break;
					case 99:
						{return ops.unidadId();}
					case -93:
						break;
					case 100:
						{return ops.unidadId();}
					case -94:
						break;
					case 101:
						{return ops.unidadId();}
					case -95:
						break;
					case 102:
						{return ops.unidadId();}
					case -96:
						break;
					case 103:
						{return ops.unidadId();}
					case -97:
						break;
					case 104:
						{return ops.unidadId();}
					case -98:
						break;
					case 105:
						{return ops.unidadId();}
					case -99:
						break;
					case 106:
						{return ops.unidadId();}
					case -100:
						break;
					case 107:
						{return ops.unidadId();}
					case -101:
						break;
					case 108:
						{return ops.unidadId();}
					case -102:
						break;
					case 109:
						{return ops.unidadId();}
					case -103:
						break;
					case 110:
						{return ops.unidadId();}
					case -104:
						break;
					case 111:
						{return ops.unidadId();}
					case -105:
						break;
					case 112:
						{return ops.unidadId();}
					case -106:
						break;
					case 113:
						{return ops.unidadId();}
					case -107:
						break;
					case 114:
						{return ops.unidadId();}
					case -108:
						break;
					case 115:
						{return ops.unidadId();}
					case -109:
						break;
					case 116:
						{return ops.unidadId();}
					case -110:
						break;
					case 117:
						{return ops.unidadId();}
					case -111:
						break;
					case 118:
						{return ops.unidadId();}
					case -112:
						break;
					case 119:
						{return ops.unidadId();}
					case -113:
						break;
					case 120:
						{return ops.unidadId();}
					case -114:
						break;
					case 121:
						{return ops.unidadId();}
					case -115:
						break;
					case 122:
						{return ops.unidadId();}
					case -116:
						break;
					case 123:
						{return ops.unidadId();}
					case -117:
						break;
					case 124:
						{return ops.unidadId();}
					case -118:
						break;
					case 125:
						{return ops.unidadId();}
					case -119:
						break;
					case 126:
						{return ops.unidadId();}
					case -120:
						break;
					case 127:
						{return ops.unidadId();}
					case -121:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
