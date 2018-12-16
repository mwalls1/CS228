package edu.iastate.cs228.hw10;

import java.awt.Point;
import java.lang.reflect.Field;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * 
 * @author Steven Dirth
 *
 */
public class XYTreeTest {
	private XYTree<Point> tree = new XYTree<>();
	private Point[] testPoints = new Point[]{
			new Point(5, 3), 
			new Point(5, 2), 
			new Point(10, 7), 
			new Point(9, 1), 
			new Point(6, 8), 
			new Point(6, 8), 
			new Point(10, 1)
		};
	@Test
	public void testAdd(){
		tree.addPoint( new Point( 5, 3 ) );
		assertEquals( new Point( 5, 3 ), tree.getRootData() );
	}
	
	@Test
	public void testContains(){
		testAdd();
		assertTrue( tree.contains( new Point( 5, 3 ) ) );
		assertFalse( tree.contains( new Point( 5, 2 ) ) );
	}
	
	@Test
	public void testAddAndContains(){
		tree.addPoint( new Point( 5, 3 ) );
		assertFalse( tree.contains( new Point( 5, 2 ) ) );
		tree.addPoint( new Point( 5, 2 ) );
		assertTrue( tree.contains( new Point( 5, 3 ) ) );
		assertTrue( tree.contains( new Point( 5, 2 ) ) );
	}
	
	@Test
	public void testLevelOrderTraverseComment(){
		tree.addAllPoints( testPoints );
		String[] expected = new String[]{"(5, 3)", "(5, 2)", "(10, 7)", "(9, 1)", "(6, 8)", "(10, 1)"};
		String[] result = new String[0];
		result = tree.levelOrderTraverse().toArray( result );
		assertArrayEquals( expected, result );
	}
	
	@Test
	public void testCanvasTree() {
		String[] expected = new String[]{"(5, 3)"};
		
		tree.addPoint( testPoints[0] );
		assertTrue( tree.contains( testPoints[0] ) );
		assertEquals( testPoints[0], tree.getRootData() );
		assertArrayEquals( expected, tree.levelOrderTraverse().toArray() );
		
		BinaryNode<Point> root = getRoot();
		
		expected = new String[]{"(5, 3)", "(5, 2)"};
		tree.addPoint( testPoints[1] );
		assertTrue( root.hasLeftChild() );
		assertFalse( root.hasRightChild() );
		assertArrayEquals( expected, tree.levelOrderTraverse().toArray() );

		expected = new String[]{"(5, 3)", "(5, 2)", "(10, 7)"};
		tree.addPoint( testPoints[2] );
		assertTrue( root.hasLeftChild() );
		assertTrue( root.hasRightChild() );
		assertArrayEquals( expected, tree.levelOrderTraverse().toArray() );

		expected = new String[]{"(5, 3)", "(5, 2)", "(10, 7)", "(9, 1)"};
		tree.addPoint( testPoints[3] );
		BinaryNode<Point> rightChild = root.getRightChild();
		assertTrue( rightChild.hasLeftChild() );
		assertFalse( rightChild.hasRightChild() );
		assertArrayEquals( expected, tree.levelOrderTraverse().toArray() );

		expected = new String[]{"(5, 3)", "(5, 2)", "(10, 7)", "(9, 1)", "(6, 8)"};
		tree.addPoint( testPoints[4] );
		assertTrue( rightChild.hasLeftChild() );
		assertTrue( rightChild.hasRightChild() );
		assertArrayEquals( expected, tree.levelOrderTraverse().toArray() );

		
		tree.addPoint( testPoints[5] ); // does nothing
		assertArrayEquals( expected, tree.levelOrderTraverse().toArray() );

		expected = new String[]{"(5, 3)", "(5, 2)", "(10, 7)", "(9, 1)", "(6, 8)", "(10, 1)"};
		tree.addPoint( testPoints[6] );
		BinaryNode<Point> rightLeftChild = rightChild.getLeftChild();
		assertFalse( rightLeftChild.hasLeftChild() );
		assertTrue( rightLeftChild.hasRightChild() );
		assertArrayEquals( expected, tree.levelOrderTraverse().toArray() );
	}
	
	@SuppressWarnings("unchecked")
	private BinaryNode<Point> getRoot(){
		try{
			Field f = tree.getClass().getDeclaredField( "root" );
			f.setAccessible( true );
			return (BinaryNode<Point>) f.get( tree );
		}catch( Exception e ){};
		return null;
	}
}