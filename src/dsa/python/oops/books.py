# Date: 2026-10-05
# link: https://algomaster.io/practice/low-level-design/design-library-book


class Book:
    def __init__(self, title: str, author: str, isbn: str):
        self._status = "Available"
        self._title = title
        self._author = author
        self._isbn = isbn

    def borrow(self) -> bool:
        if self._status == "Borrowed":
            return False
        else:
            self._status = "Borrowed"
            return True

    def returnBook(self) -> bool:
        if self._status == "Available":
            return False
        else:
            self._status = "Available"
            return True

    def isAvailable(self) -> bool:
        # return not self.borrow()
        return self._status == "Available"

    def getInfo(self) -> str:
        return f"{self._title} by {self._author} (ISBN: {self._isbn}) - {self._status}"


# Your Book object will be instantiated and called as such:
# obj = Book(title, author, isbn)
# param_1 = obj.borrow()
# param_2 = obj.returnBook()
# param_3 = obj.isAvailable()
# param_4 = obj.getInfo()
