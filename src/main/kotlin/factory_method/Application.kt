package factory_method


abstract class Application {
    abstract fun createDocument(): Document

    companion object {
        fun getApplicationDocument(documentType: DocumentType): Application =
            when (documentType) {
                DocumentType.DRAWING -> DrawingApplication()
                DocumentType.TEXT -> TextApplication()
                else -> throw Exception("Invalid document type")
            }
    }
}

class DrawingApplication : Application() {
    override fun createDocument(): Document = DrawingDocument()
}

class DrawingDocument : Document {
    override fun showDocumentInfo() {
        println("This is drawing document")
    }
}
enum class DocumentType {
    DRAWING,
    TEXT
}

interface Document {
    fun showDocumentInfo()
}

class TextApplication : Application() {
    override fun createDocument(): Document = TextDocument()
}

class TextDocument : Document {
    override fun showDocumentInfo() {
        println("This is text document")
    }
}

fun main() {
    val drawingApplication = Application.getApplicationDocument(DocumentType.DRAWING)
    val drawingDocument = drawingApplication.createDocument()
    drawingDocument.showDocumentInfo()

    val textApplication = Application.getApplicationDocument(DocumentType.TEXT)
    val textDocument = textApplication.createDocument()
    textDocument.showDocumentInfo()
}