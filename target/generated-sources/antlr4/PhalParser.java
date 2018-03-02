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
		Includes=1, Include=2, Setup=3, SetupCnt=4, Exprs=5, Expr=6, SetupDcls=7, 
		VarDcls=8, VarDcl=9, Type=10, CmpDcls=11, CmpDcl=12, AdvType=13, Groups=14, 
		Group=15, GrpCnts=16, GrpCnt=17, Stmts=18, Stmt=19, Selective=20, Switch=21, 
		CaseList=22, Cases=23, Case=24, DefaultCase=25, IfStmt=26, Iterative=27, 
		Loop=28, FuncCall=29, CallParams=30, CallParam=31, Assignment=32, AssStmt=33, 
		CompOp=34, Oper=35, LogicalStmt=36, LogicOper=37, Repeat=38, RepeatCnt=39, 
		Funcs=40, Func=41, FuncContent=42, Params=43, Param=44, ReturnStmt=45;
	public static final int
		RULE_r = 0;
	public static final String[] ruleNames = {
		"r"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Includes", "Include", "Setup", "SetupCnt", "Exprs", "Expr", "SetupDcls", 
		"VarDcls", "VarDcl", "Type", "CmpDcls", "CmpDcl", "AdvType", "Groups", 
		"Group", "GrpCnts", "GrpCnt", "Stmts", "Stmt", "Selective", "Switch", 
		"CaseList", "Cases", "Case", "DefaultCase", "IfStmt", "Iterative", "Loop", 
		"FuncCall", "CallParams", "CallParam", "Assignment", "AssStmt", "CompOp", 
		"Oper", "LogicalStmt", "LogicOper", "Repeat", "RepeatCnt", "Funcs", "Func", 
		"FuncContent", "Params", "Param", "ReturnStmt"
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
		public TerminalNode Includes() { return getToken(PhalParser.Includes, 0); }
		public TerminalNode Setup() { return getToken(PhalParser.Setup, 0); }
		public TerminalNode Repeat() { return getToken(PhalParser.Repeat, 0); }
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2);
			match(Includes);
			setState(3);
			match(Setup);
			setState(4);
			match(Repeat);
			setState(5);
			match(Funcs);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\n\4\2\t\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\2\2\3\2\2\2\2\b\2\4\3\2\2\2\4\5\7\3\2\2\5\6\7\5\2\2"+
		"\6\7\7(\2\2\7\b\7*\2\2\b\3\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}