// Generated from Phal.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PhalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Includes=1, Include=2, Setup=3, SetupCnts=4, SetupCnt=5, Dcl=6, VarDcls=7, 
		VarDcl=8, Type=9, CmpDcls=10, CmpDcl=11, AdvType=12, Groups=13, Group=14, 
		GrpCnts=15, GrpCnt=16, Stmts=17, Stmt=18, Selective=19, Switch=20, CaseList=21, 
		Cases=22, Case=23, DefaultCase=24, IfStmt=25, Iterative=26, Loop=27, FuncCall=28, 
		CallParams=29, CallParam=30, Assignment=31, AssStmt=32, CompOp=33, Oper=34, 
		LogicalStmt=35, LogicOper=36, Repeat=37, RepeatCnts=38, RepeatCnt=39, 
		Funcs=40, Func=41, FuncContents=42, FuncContent=43, Params=44, Param=45, 
		ReturnStmt=46, LEFTBRACKET=47, RIGHTBRACKET=48, ID=49, NONE=50, TEXTVALUE=51, 
		NUMBERVALUE=52, BOOLVALUE=53;
	public static final int
		RULE_r = 0;
	public static final String[] ruleNames = {
		"r"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "'{'", 
		"'}'", null, "'none'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Includes", "Include", "Setup", "SetupCnts", "SetupCnt", "Dcl", 
		"VarDcls", "VarDcl", "Type", "CmpDcls", "CmpDcl", "AdvType", "Groups", 
		"Group", "GrpCnts", "GrpCnt", "Stmts", "Stmt", "Selective", "Switch", 
		"CaseList", "Cases", "Case", "DefaultCase", "IfStmt", "Iterative", "Loop", 
		"FuncCall", "CallParams", "CallParam", "Assignment", "AssStmt", "CompOp", 
		"Oper", "LogicalStmt", "LogicOper", "Repeat", "RepeatCnts", "RepeatCnt", 
		"Funcs", "Func", "FuncContents", "FuncContent", "Params", "Param", "ReturnStmt", 
		"LEFTBRACKET", "RIGHTBRACKET", "ID", "NONE", "TEXTVALUE", "NUMBERVALUE", 
		"BOOLVALUE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Phal.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PhalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RContext extends ParserRuleContext {
		public TerminalNode Setup() { return getToken(PhalParser.Setup, 0); }
		public TerminalNode Repeat() { return getToken(PhalParser.Repeat, 0); }
		public TerminalNode Includes() { return getToken(PhalParser.Includes, 0); }
		public TerminalNode Funcs() { return getToken(PhalParser.Funcs, 0); }
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhalListener ) ((PhalListener)listener).enterR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhalListener ) ((PhalListener)listener).exitR(this);
		}
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_r);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(3);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Includes) {
				{
				setState(2);
				match(Includes);
				}
			}

			setState(5);
			match(Setup);
			setState(6);
			match(Repeat);
			setState(8);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Funcs) {
				{
				setState(7);
				match(Funcs);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\67\r\4\2\t\2\3\2"+
		"\5\2\6\n\2\3\2\3\2\3\2\5\2\13\n\2\3\2\2\2\3\2\2\2\2\r\2\5\3\2\2\2\4\6"+
		"\7\3\2\2\5\4\3\2\2\2\5\6\3\2\2\2\6\7\3\2\2\2\7\b\7\5\2\2\b\n\7\'\2\2\t"+
		"\13\7*\2\2\n\t\3\2\2\2\n\13\3\2\2\2\13\3\3\2\2\2\4\5\n";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}