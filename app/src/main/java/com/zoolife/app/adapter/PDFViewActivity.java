package com.zoolife.app.adapter;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;
import com.zoolife.app.R;
import com.zoolife.app.activity.AppBaseActivity;

import java.util.List;

public class PDFViewActivity extends AppBaseActivity implements OnPageChangeListener, OnLoadCompleteListener {

    private static final String TAG = PDFViewActivity.class.getSimpleName();
    public static String SAMPLE_FILE = "sample_pdf.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_view);

        pdfView = (PDFView) findViewById(R.id.pdfView);


        SAMPLE_FILE = getIntent().getStringExtra("file");
        displayFromAsset(SAMPLE_FILE + ".pdf");

        TextView titleText = (TextView) findViewById(R.id.toolbar_title);
        titleText.setText(getIntent().getStringExtra("title"));
        titleText.setTextColor(ContextCompat.getColor(this, R.color.app_bg));

        findViewById(R.id.backBtn).setOnClickListener(view -> {
            finish();
        });

        setLightStatusBar();
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(pdfFileName)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}