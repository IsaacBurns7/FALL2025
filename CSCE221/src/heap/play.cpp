#include <iostream>
#include <vector>

using std::cout, std::endl;


class BinaryHeap
{
    private:
    int curSize; // number of elements
    bool orderOK; // true for heap-order property
    std::vector<int> array; //(dynamic) heap array
    static const int DEF_SIZE = 4; // default size
    // other private functions
    void checkSize();
    void walkDown(int hole);
    public: // class BinaryHeap (cont)
    // default constructor
    BinaryHeap(int size = DEF_SIZE) : curSize(0), orderOK(true),
    array(size) {}
    int findMin()
    {
        if (isEmpty())
            throw std::out_of_range("EMPTY!!!");
        if (!orderOK)
            buildHeap();
        return array[0];
    }
    bool isEmpty() const { return curSize == 0; }
    void buildHeap();
    void toss(int x);
    void insert(int x);
    void deleteMin();
};

// double the heap array if it is full
void BinaryHeap::checkSize()
{
    if (curSize == array.size())
    {
        array.resize(2 * curSize);
    }
}
// Insert Into Array Without Maintaining Heap Order
void BinaryHeap::toss(int x)
{
    checkSize();
    array[curSize++] = x;
    if (array[(curSize - 1) / 2] > x)
        orderOK = false;
}

void BinaryHeap::insert(int x)
{
    checkSize();
    // walk up (establish heap order now)
    int hole = curSize++;
    for (; hole > 0 && array[(hole - 1) / 2] > x; hole = (hole - 1) / 2)
        array[hole] = array[(hole - 1) / 2];
    array[hole] = x;
}
void BinaryHeap::deleteMin()
{
    --curSize;
    array[0] = array[curSize];
    walkDown(0);
}
void BinaryHeap::walkDown(int hole)
{
    int child;
    int key = array[hole];
    for (; 2 * hole + 1 < curSize; hole = child)
    {
        child = 2 * hole + 1; // left child = 2*hole+1
        if (child != curSize - 1 && array[child] > array[child + 1])
            child++; // right child = 2*hole+2
        if (key > array[child])
            array[hole] = array[child];
        else
            break;
    }
    array[hole] = key;
}

void BinaryHeap::buildHeap()
{
    for (int i = (curSize - 2) / 2; i >= 0; i--)
        walkDown(i);
    orderOK = true;
}

void heapsort(vector<int> &A) {
    //build heap
    int length = A.size();
    for (int i = (length-2)/2; i >= 0; i--)
        walkDown(A, i, length);
    for (int i = length-1; i > 0; i--) {
        swapElems(A, 0, i); // swap A[0] and A[i]
        walkDown(A, 0, i);
    }
}
void swapElems(vector<int> &A, int i1, int i2) {
    int tmp = A[i1];
    A[i1] = A[i2];
    A[i2] = tmp;
}

void walkDown(vector<int> &A, int index, int size) {
    int k, child;
    int key = A[index];
    for (k = index ; 2*k+1 < size; k = child) {
        child = 2*k+1; //left child
        if (child != size-1 && A[child] < A[child+1])
            child++; // right child = 2k+2
        if (key < A[child]) A[k] = A[child];
        else break;
    }
    A[k] = key;
}

int main()
{
    BinaryHeap heap;
    heap.toss(9);
    heap.buildHeap();
    heap.insert(6);
    cout << "Min: " << heap.findMin() << endl;
    heap.deleteMin();
    cout << "Min: " << heap.findMin() << endl;
    return 0;
}