package org.mozilla.javascript.tests;

import org.junit.Before;
import org.junit.Test;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ast.AstRoot;

import java.io.IOException;
import java.io.StringReader;
import java.lang.String;

import static junit.framework.Assert.assertEquals;

public class Bug798642Test {
    private CompilerEnvirons environment;

    @Before
    public void setUp() throws Exception {
        environment = new CompilerEnvirons();
        environment.setLanguageVersion(180);
        environment.setStrictMode(false);
    }

    @Test
    public void testGetterSetterFunctionSyntax() throws IOException {
        Parser parser = new Parser(environment);
      
        String script =       
              "var o = {_x: 123, get x() {\n" +
              "  return this._x;\n" +
              "}, set x(value) {\n" +
              "  this._x = value;\n" +
              "}};\n";
        
        AstRoot astRoot = parser.parse(new StringReader(script), null, 1);
        assertEquals(script, astRoot.toSource());
    }
}
