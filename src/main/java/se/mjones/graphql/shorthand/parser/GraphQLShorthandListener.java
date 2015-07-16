// Generated from GraphQLShorthand.g4 by ANTLR 4.5.1
package se.mjones.graphql.shorthand.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GraphQLShorthandParser}.
 */
public interface GraphQLShorthandListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(GraphQLShorthandParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(GraphQLShorthandParser.SchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(GraphQLShorthandParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(GraphQLShorthandParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#enumDefinition}.
	 * @param ctx the parse tree
	 */
	void enterEnumDefinition(GraphQLShorthandParser.EnumDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#enumDefinition}.
	 * @param ctx the parse tree
	 */
	void exitEnumDefinition(GraphQLShorthandParser.EnumDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#unionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterUnionDefinition(GraphQLShorthandParser.UnionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#unionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitUnionDefinition(GraphQLShorthandParser.UnionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinition(GraphQLShorthandParser.TypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinition(GraphQLShorthandParser.TypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#interfaceDefinition}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceDefinition(GraphQLShorthandParser.InterfaceDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#interfaceDefinition}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceDefinition(GraphQLShorthandParser.InterfaceDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(GraphQLShorthandParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(GraphQLShorthandParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(GraphQLShorthandParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(GraphQLShorthandParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(GraphQLShorthandParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(GraphQLShorthandParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#fieldType}.
	 * @param ctx the parse tree
	 */
	void enterFieldType(GraphQLShorthandParser.FieldTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#fieldType}.
	 * @param ctx the parse tree
	 */
	void exitFieldType(GraphQLShorthandParser.FieldTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(GraphQLShorthandParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(GraphQLShorthandParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#listType}.
	 * @param ctx the parse tree
	 */
	void enterListType(GraphQLShorthandParser.ListTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#listType}.
	 * @param ctx the parse tree
	 */
	void exitListType(GraphQLShorthandParser.ListTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GraphQLShorthandParser#nonNullType}.
	 * @param ctx the parse tree
	 */
	void enterNonNullType(GraphQLShorthandParser.NonNullTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GraphQLShorthandParser#nonNullType}.
	 * @param ctx the parse tree
	 */
	void exitNonNullType(GraphQLShorthandParser.NonNullTypeContext ctx);
}