// Generated from GraphQLShorthand.g4 by ANTLR 4.5.1
package se.mjones.graphql.shorthand.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GraphQLShorthandParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GraphQLShorthandVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#schema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchema(GraphQLShorthandParser.SchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(GraphQLShorthandParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#enumDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDefinition(GraphQLShorthandParser.EnumDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#unionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionDefinition(GraphQLShorthandParser.UnionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#typeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinition(GraphQLShorthandParser.TypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#interfaceDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceDefinition(GraphQLShorthandParser.InterfaceDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(GraphQLShorthandParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(GraphQLShorthandParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(GraphQLShorthandParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#fieldType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldType(GraphQLShorthandParser.FieldTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(GraphQLShorthandParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#listType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListType(GraphQLShorthandParser.ListTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GraphQLShorthandParser#nonNullType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonNullType(GraphQLShorthandParser.NonNullTypeContext ctx);
}