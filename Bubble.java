package Ordenamiento;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
       
        int[] numerosAleatorios = {
            587, 254, 503, 818, 237, 215, 122, 653, 774, 202, 890, 657, 109, 859, 305, 789, 141, 788, 147, 598, 
            227, 133, 423, 947, 969, 27, 792, 572, 908, 339, 746, 243, 181, 279, 428, 96, 647, 545, 872, 174, 
            479, 934, 25, 931, 922, 678, 110, 292, 617, 104, 741, 726, 823, 503, 736, 581, 63, 852, 470, 121, 
            419, 866, 283, 328, 336, 454, 196, 461, 473, 77, 764, 612, 468, 855, 962, 798, 287, 25, 394, 204, 
            989, 293, 305, 361, 307, 154, 319, 803, 108, 538, 396, 227, 262, 949, 69, 123, 18, 255, 979, 493, 
            657, 581, 71
        };

        System.out.println("Números aleatorios:");
        for (int i = 0; i < numerosAleatorios.length; i++) {
            System.out.print(numerosAleatorios[i] + " ");
        }
        System.out.println("\n");

        // Bubble Sort
        long startTime = System.nanoTime();
        int[] bubbleSorted = bubbleSort(numerosAleatorios.clone());
        long endTime = System.nanoTime();
        System.out.println("Bubble Sort:");
        printArray(bubbleSorted);
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ns\n");

        // Merge Sort
        startTime = System.nanoTime();
        int[] mergeSorted = mergeSort(numerosAleatorios.clone());
        endTime = System.nanoTime();
        System.out.println("Merge Sort:");
        printArray(mergeSorted);
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ns\n");

        // Quick Sort
        startTime = System.nanoTime();
        int[] quickSorted = quickSort(numerosAleatorios.clone(), 0, numerosAleatorios.length - 1);
        endTime = System.nanoTime();
        System.out.println("Quick Sort:");
        printArray(quickSorted);
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ns\n");

        // AVL Tree Sort
        AVLTree avlTree = new AVLTree();
        startTime = System.nanoTime();
        for (int num : numerosAleatorios.clone()) {
            avlTree.insert(num);
        }
        System.out.println("AVL Tree Sort:");
        avlTree.inOrderTraversal();
        endTime = System.nanoTime();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ns");
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    // Bubble Sort
    private static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    // Merge Sort
    private static int[] mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    arr[k++] = left[i++];
                } else {
                    arr[k++] = right[j++];
                }
            }

            while (i < left.length) {
                arr[k++] = left[i++];
            }

            while (j < right.length) {
                arr[k++] = right[j++];
            }
        }
        return arr;
    }

    // Quick Sort
    private static int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        return arr;
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // AVL Tree Sort
    static class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    static class AVLTree {
        Node root;
        
         int height(Node N) {
                    if (N == null)
                        return 0;
                    return N.height;
                }
        
                int max(int a, int b) {
                    return (a > b) ? a : b;
                }
        
                Node rightRotate(Node y) {
                    Node x = y.left;
                    Node T2 = x.right;
        
                    x.right = y;
                    y.left = T2;
        
                    y.height = max(height(y.left), height(y.right)) + 1;
                    x.height = max(height(x.left), height(x.right)) + 1;
        
                    return x;
                }
        
                Node leftRotate(Node x) {
                    Node y = x.right;
                    Node T2 = y.left;
        
                    y.left = x;
                    x.right = T2;
        
                    x.height = max(height(x.left), height(x.right)) + 1;
                    y.height = max(height(y.left), height(y.right)) + 1;
        
                    return y;
                }
        
                int getBalance(Node N) {
                    if (N == null)
                        return 0;
                    return height(N.left) - height(N.right);
                }
        
                Node insert(Node node, int key) {
                    if (node == null)
                        return (new Node(key));
        
                    if (key < node.key)
                        node.left = insert(node.left, key);
                    else if (key > node.key)
                        node.right = insert(node.right, key);
                    else
                        return node;
        
                    node.height = 1 + max(height(node.left), height(node.right));
        
                    int balance = getBalance(node);
        
                    if (balance > 1 && key < node.left.key)
                        return rightRotate(node);
        
                    if (balance < -1 && key > node.right.key)
                        return leftRotate(node);
        
                    if (balance > 1 && key > node.left.key) {
                        node.left = leftRotate(node.left);
                        return rightRotate(node);
                    }
        
                    if (balance < -1 && key < node.right.key) {
                        node.right = rightRotate(node.right);
                        return leftRotate(node);
                    }
        
                    return node;
                }
        
                void inOrderTraversal() {
                    inOrderTraversal(root);
                    System.out.println();
                }
        
                void inOrderTraversal(Node root) {
                    if (root != null) {
                        inOrderTraversal(root.left);
                        System.out.print(root.key + " ");
                        inOrderTraversal(root.right);
                    }
                }
        
             void insert(int key) {
                    root = insert(root, key);
                }
            }
        }
        
        
