/**
 * 
 */
package binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Aswin
 *
 */
public class BinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);

		TreeNode left1 = root.left;
		left1.left = new TreeNode(4);
		left1.right = new TreeNode(5);

//		TreeNode LRight2 = left1.right;
//		LRight2.left = new TreeNode(6);
//		LRight2.right = new TreeNode(7);

		TreeNode right1 = root.right;
		right1.left = new TreeNode(15);
		right1.right= new TreeNode(7);

//		System.out.println("Diameter of the Tree: "+getDiameter(root));
		
//		System.out.println("Inorder Rec");
//		inOrderRec(root);
//		System.out.println("\nInorder Stack");
//		inOrderStack(root);
		
//		System.out.println("Pre Order Rec");
//		preOrderRec(root);
//		System.out.println("\nPre Order stack");
//		preOrderStack(root);
		
		List<List<Integer>> lists = levelOrder(root);
		System.out.println(lists);
//		System.out.println(getHeight(root));

	}

	public static int getDiameter(TreeNode root){
		int left = diameter(root.left);
		int right = diameter(root.right);

		return left+right+1;
	}
	public static int diameter(TreeNode root){
		if(root == null) return 0;
		return Math.max(diameter(root.left), diameter(root.right))+1;
	}

	public static void inOrderStack(TreeNode root){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode curr = root;
		
		if(root == null) return;
		
			
		while(!stack.isEmpty() ||curr != null){
			if(curr != null){
				stack.push(curr);
				curr = curr.left;
			}else{
				if (!stack.isEmpty())
					curr = stack.pop();
				System.out.print(curr.val + " ");
				curr = curr.right;
			}				
		}
	}
	
	public static void preOrderRec(TreeNode root){
		if(root == null) return;
		System.out.print(root.val + " ");
		preOrderRec(root.left);
		preOrderRec(root.right);
	}
	
	public static void inOrderRec(TreeNode root){
		if(root == null) return;
		inOrderRec(root.left);
		System.out.print(root.val + " ");
		inOrderRec(root.right);
	}
	
	public static void preOrderStack(TreeNode root){
		if(root == null) return;
		TreeNode curr = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(!stack.isEmpty() || curr!=null){
			if(curr != null){
				System.out.print(curr.val + " ");
				stack.push(curr);
				curr = curr.left;
			}
			else{
				if(!stack.isEmpty()){
					curr = stack.pop();
					curr =curr.right;
				}
			}
			
		}
	}
	
	public static int getHeight(TreeNode root){
		if(root != null){
			return Math.max(getHeight(root.left)+1, getHeight(root.right)+1);
		}
		
		return 0;
	}
	
	public static List<List<Integer>> zigzagLevelOrder(TreeNode root){
		if(root == null)
			return new LinkedList<List<Integer>>();
		
		boolean leftRight = false;
		List<List<Integer>> lists = new LinkedList<List<Integer>>();
		int height = getHeight(root);
		zigzagLevelOrder1(root, leftRight, lists, height);
		List<Integer> list = new LinkedList<Integer>();
		list.add(root.val);
		lists.add(0, list);
		return lists;
	}
	
	public static void zigzagLevelOrder(TreeNode root, boolean leftRight, List<List<Integer>> mq){ 
		if(root != null){
			List<Integer> levelList = new LinkedList<Integer>(); 
			if(leftRight){
				if(root.left != null){
					levelList.add(root.left.val);
				}
				
				if(root.right != null){
					levelList.add(root.right.val);
				}
			}else{
				if(root.right != null){
					levelList.add(root.right.val);
				}
				if(root.left != null){
					levelList.add(root.left.val);
				}
			}
			if(levelList.size() != 0)
				mq.add(levelList);
			
			if(leftRight){
				zigzagLevelOrder(root.right, !leftRight, mq);
				zigzagLevelOrder(root.left, !leftRight, mq);
			}else{
				zigzagLevelOrder(root.left, !leftRight, mq);
				zigzagLevelOrder(root.right, !leftRight, mq);				
			}
		}
		
//		mq.add(new LinkedList<Integer>());
	}
	
	public static void zigzagLevelOrder1(TreeNode root, boolean leftRight, List<List<Integer>> lists, int level){
		if(root != null){
			List<Integer> list = new LinkedList<Integer>();
			if(root.left != null){
				list.add(root.left.val);
			}
			if(root.right != null){
				list.add(root.right.val);
			}
			
			if(leftRight){
				zigzagLevelOrder1(root.right,!leftRight, lists, level-1);
				zigzagLevelOrder1(root.left,!leftRight, lists, level-1);
			}else{
				Collections.reverse(list);
				lists.add(list);
				zigzagLevelOrder1(root.left,!leftRight, lists, level-1);
				zigzagLevelOrder1(root.right,!leftRight, lists, level-1);
			}			
		}
	}
	
	public static List<List<Integer>> levelOrder(TreeNode root){
		List<List<Integer>> result = new ArrayList();
		levelOrder(result, root, 0);
		return result;
	}
	
	public static void levelOrder(List<List<Integer>> result, TreeNode root, int level){
		if(root == null)
			return;
		if(result.size() <= level){
			List<Integer> tmp = new ArrayList<Integer>();
			tmp.add(root.val);
			result.add(tmp);
		}else{
			result.get(level).add(root.val);
		}
		levelOrder(result, root.left, level+1);
		levelOrder(result, root.right, level+1);
	}
	
	

}
