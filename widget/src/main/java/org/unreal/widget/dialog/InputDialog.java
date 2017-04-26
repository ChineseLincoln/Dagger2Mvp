package org.unreal.widget.dialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import org.unreal.widget.R;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
/**
 * <b>类名称：</b> InputDialog <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月21日 10:43<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class InputDialog {

    private AlertDialog dialog;
    private EditText editText;

    private InputDialog(AlertDialog dialog, EditText editText) {
        this.dialog = dialog;
        this.editText = editText;
    }

    public void show() {
        dialog.show();
    }

    /**
     * Dismiss this dialog, removing it from the screen. This method can be
     * invoked safely from any thread.
     */
    public void dismiss() {
        dialog.dismiss();
    }

    /**
     * Hide the dialog, but not dismiss it.
     */
    public void hide() {
        dialog.hide();
    }

    /**
     * Cancel the dialog.  This is essentially the same as calling {@link #dismiss()}, but it will
     * also call your {@link DialogInterface.OnCancelListener} (if registered).
     */
    public void cancel() {
        dialog.cancel();
    }

    /**
     * Retrieve the current Window for the activity.  This can be used to
     * directly access parts of the Window API that are not available
     * through Activity/Screen.
     *
     * @return Window The current window, or null if the activity is not
     *         visual.
     */
    public Window getWindow() {
        return dialog.getWindow();
    }

    /**
     * Retrieve the Context this Dialog is running in.
     *
     * @return Context The Context used by the Dialog.
     */
    public Context getContext() {
        return dialog.getContext();
    }

    /**
     * Sets a listener to be invoked when the dialog is shown.
     * @param listener The {@link DialogInterface.OnShowListener} to use.
     */
    public void setOnShowListener(DialogInterface.OnShowListener listener) {
        dialog.setOnShowListener(listener);
    }

    /**
     * Set resId to 0 if you don't want an icon.
     *
     * @param resId the resourceId of the drawable to use as the icon or 0
     *              if you don't want an icon.
     */
    public void setIcon(int resId) {
        dialog.setIcon(resId);
    }

    public void setIcon(Drawable res) {
        dialog.setIcon(res);
    }

    /**
     * Set an icon as supplied by a theme attribute. e.g. android.R.attr.alertDialogIcon
     *
     * @param attrId ID of a theme attribute that points to a drawable resource.
     */
    public void setIconAttributes(int attrId) {
        dialog.setIconAttribute(attrId);
    }

    /**
     * Set the title text for this dialog's window.
     *
     * @param title The new text to display in the title.
     */
    public void setTitle(CharSequence title) {
        dialog.setTitle(title);
    }

    /**
     * Set the title text for this dialog's window. The text is retrieved
     * from the resources with the supplied identifier.
     *
     * @param titleId the title's text resource identifier
     */
    public void setTitle(int titleId) {
        dialog.setTitle(titleId);
    }

    /**
     * @see Builder#setCustomTitle(View)
     */
    public void setCustomTitle(View customTitleView) {
        dialog.setCustomTitle(customTitleView);
    }

    /**
     * Gets one of the buttons used in the dialog. Returns null if the specified
     * button does not exist or the dialog has not yet been fully created (for
     * example, via {@link #show()}
     *
     * @param whichButton The identifier of the button that should be returned.
     *                    For example, this can be
     *                    {@link DialogInterface#BUTTON_POSITIVE}.
     * @return The button from the dialog, or null if a button does not exist.
     */
    public Button getButton(int whichButton) {
        return dialog.getButton(whichButton);
    }

    /**
     * @return Whether the dialog is currently showing.
     */
    public boolean isShowing() {
        return dialog.isShowing();
    }

    /**
     * Finds a child view with the given identifier. Returns null if the
     * specified child view does not exist or the dialog has not yet been fully
     * created (for example, via {@link #show()}
     *
     * @param id the identifier of the view to find
     * @return The view with the given id or null.
     */
    public View findViewById(int id) {
        return dialog.findViewById(id);
    }

    /**
     * Retrieve the current text content in the input box of dialog.
     * @return The text content.
     */
    public CharSequence getInputText() {
        Editable editable = editText.getText();
        return editable.toString();
    }

    public static class Builder {

        private InputDialog inputDialog; // the proxy dialog
        private AlertDialog.Builder builder;
        private AlertDialog alertDialog;

        private ButtonActionListener positiveButtonActionListener;
        private ButtonActionListener negativeButtonActionListener;
        private ButtonActionListener neutralButtonActionListener;
        private OnCancelListener onCancelListener;
        private OnDismissListener onDismissListener;
        private ButtonActionIntercepter buttonActionIntercepter;

        private int customizedLayoutResId;

        private int editTextId;
        private CharSequence inputDefaultText;

        private CharSequence inputHint;
        private int inputMaxWords = -1;
        private boolean interceptAutoPopupInputMethod;

        private ButtonHandler mHandler;

        public Builder(Context context) {
            builder = new AlertDialog.Builder(context);
        }

        public Builder(Context context, int style) {
            builder = new AlertDialog.Builder(context, style);
        }

        public Builder setTitle(CharSequence title) {
            builder.setTitle(title);
            return this;
        }

        public Builder setInputDefaultText(CharSequence text) {
            inputDefaultText = text;
            return this;
        }

        public Builder setInputHint(CharSequence text) {
            inputHint = text;
            return this;
        }

        /**
         * Set max words limitation of the input box.
         */
        public Builder setInputMaxWords(int maxWords) {
            this.inputMaxWords = maxWords;
            return this;
        }

        /**
         * Set a listener to be invoked when the positive button of the dialog is pressed.
         *
         * @param textId   The resource id of the text to display in the positive button
         * @param listener The {@link ButtonActionListener} to use.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setPositiveButton(final int textId, final ButtonActionListener listener) {
            positiveButtonActionListener = listener;
            builder.setPositiveButton(textId, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean intercept = onInterceptButtonAction(which, inputDialog.getInputText());
                    if (!intercept) {
                        if (positiveButtonActionListener != null) {
                            positiveButtonActionListener.onClick(inputDialog.getInputText());
                        }
                    }
                }
            });
            return this;
        }

        /**
         * Set a listener to be invoked when the positive button of the dialog is pressed.
         *
         * @param text     The text to display in the positive button
         * @param listener The {@link ButtonActionListener} to use.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setPositiveButton(final CharSequence text, final ButtonActionListener listener) {
            positiveButtonActionListener = listener;
            builder.setPositiveButton(text, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean intercept = onInterceptButtonAction(which, inputDialog.getInputText());
                    if (!intercept) {
                        if (positiveButtonActionListener != null) {
                            positiveButtonActionListener.onClick(inputDialog.getInputText());
                        }
                    }
                }
            });
            return this;
        }

        /**
         * Set a listener to be invoked when the negative button of the dialog is pressed.
         *
         * @param textId   The resource id of the text to display in the negative button
         * @param listener The {@link ButtonActionListener} to use.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNegativeButton(final int textId, final ButtonActionListener listener) {
            negativeButtonActionListener = listener;
            builder.setNegativeButton(textId, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean intercept = onInterceptButtonAction(which, inputDialog.getInputText());
                    if (!intercept) {
                        if (negativeButtonActionListener != null) {
                            negativeButtonActionListener.onClick(inputDialog.getInputText());
                        }
                    }
                }
            });
            return this;
        }

        /**
         * Set a listener to be invoked when the negative button of the dialog is pressed.
         *
         * @param text     The text to display in the negative button
         * @param listener The {@link ButtonActionListener} to use.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNegativeButton(final CharSequence text, final ButtonActionListener listener) {
            negativeButtonActionListener = listener;
            builder.setNegativeButton(text, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean intercept = onInterceptButtonAction(which, inputDialog.getInputText());
                    if (!intercept) {
                        if (negativeButtonActionListener != null) {
                            negativeButtonActionListener.onClick(inputDialog.getInputText());
                        }
                    }
                }
            });
            return this;
        }

        /**
         * Set a listener to be invoked when the neutral button of the dialog is pressed.
         *
         * @param textId   The resource id of the text to display in the neutral button
         * @param listener The {@link DialogInterface.OnClickListener} to use.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNeutralButton(final int textId, final ButtonActionListener listener) {
            neutralButtonActionListener = listener;
            builder.setNeutralButton(textId, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean intercept = onInterceptButtonAction(which, inputDialog.getInputText());
                    if (!intercept) {
                        if (neutralButtonActionListener != null) {
                            neutralButtonActionListener.onClick(inputDialog.getInputText());
                        }
                    }
                }
            });
            return this;
        }

        /**
         * Set a listener to be invoked when the neutral button of the dialog is pressed.
         *
         * @param text     The text to display in the neutral button
         * @param listener The {@link DialogInterface.OnClickListener} to use.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNeutralButton(final CharSequence text, final ButtonActionListener listener) {
            neutralButtonActionListener = listener;
            builder.setNeutralButton(text, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    boolean intercept = onInterceptButtonAction(which, inputDialog.getInputText());
                    if (!intercept) {
                        if (neutralButtonActionListener != null) {
                            neutralButtonActionListener.onClick(inputDialog.getInputText());
                        }
                    }
                }
            });
            return this;
        }

        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable) {
            alertDialog.setCancelable(cancelable);
            return this;
        }

        /**
         * Sets the callback that will be called if the dialog is canceled.
         *
         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
         * being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed
         * and not just when it is canceled, see
         * {@link #setOnDismissListener(OnDismissListener)
         * setOnDismissListener}.</p>
         *
         * @return This Builder object to allow for chaining of calls to set methods
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener(OnDismissListener)
         */
        public Builder setOnCancelListener(OnCancelListener listener) {
            onCancelListener = listener;
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface d) {
                    if (onCancelListener != null) {
                        onCancelListener.onCancel(inputDialog.getInputText());
                    }
                }
            });
            return this;
        }

        /**
         * Sets the callback that will be called when the dialog is dismissed for any reason.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(OnDismissListener listener) {
            onDismissListener = listener;
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface d) {
                    hideSoftInput(alertDialog);
                    if (onDismissListener != null) {
                        onDismissListener.onDismiss(inputDialog.getInputText());
                    }
                }
            });
            return this;
        }

        /**
         * Used to intercept the inherent behaviors of AlertDialog
         * after a button is clicked.
         * The behaviors include dismiss-after-button-clicked and
         * the #onClick(CharSequence) callback.
         *
         * @param intercepter The {@link ButtonActionIntercepter} to use.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder interceptButtonAction(ButtonActionIntercepter intercepter) {
            buttonActionIntercepter = intercepter;
            return this;
        }

        /**
         * Used to disable the automatically-popup-soft-input-keyboard behavior
         * when the dialog shows.
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder disableAutoPopupSoftInput() {
            interceptAutoPopupInputMethod = true;
            return this;
        }

        /**
         * Set a custom view resource to be the contents of the Dialog. The
         * resource will be inflated, adding all top-level views to the screen.
         *
         * @param layoutResId Resource ID to be inflated.
         * @return This Builder object to allow for chaining of calls to set
         * methods
         */
        public Builder setView(int layoutResId, int editTextId) {
            customizedLayoutResId = layoutResId;
            this.editTextId = editTextId;
            return this;
        }

        /**
         * Creates a {@link InputDialog} with the arguments supplied to this builder and
         * {@link Dialog#show()}'s the dialog.
         */
        public InputDialog show() {
            initLayout();
            alertDialog = builder.show();
            initEditText(alertDialog);
            popupSoftInput(alertDialog);
            replaceHandler(alertDialog);
            return createInputDialog(alertDialog);
        }

        /**
         * Creates a {@link InputDialog} with the arguments supplied to this builder. It does not
         * {@link Dialog#show()} the dialog. This allows the user to do any extra processing
         * before displaying the dialog. Use {@link #show()} if you don't have any other processing
         * to do and want this to be created and displayed.
         */
        public InputDialog create() {
            initLayout();
            alertDialog = builder.create();
            initEditText(alertDialog);
            popupSoftInput(alertDialog);
            replaceHandler(alertDialog);
            return createInputDialog(alertDialog);
        }

        private boolean onInterceptButtonAction(int buttonType, CharSequence text) {
            if (buttonActionIntercepter != null) {
                boolean intercept = buttonActionIntercepter.onInterceptButtonAction(buttonType, text);
                if (intercept) {
                    mHandler.interceptButtonAction();
                    return true;
                }
            }
            return false;
        }

        private void initLayout() {
            if (customizedLayoutResId == 0) {
                builder.setView(R.layout.widget_dialog_input);
            } else {
                builder.setView(customizedLayoutResId);
            }
        }

        private void initEditText(AlertDialog dialog) {
            EditText input = obtainEditText(dialog);
            if (inputMaxWords >= 0) {
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(inputMaxWords)});
            }
            if (!TextUtils.isEmpty(inputDefaultText)) {
                input.setText(inputDefaultText);
                // Move text cursor to the end if there are default texts.
                int len = inputMaxWords >= 0 ? Math.min(inputMaxWords, inputDefaultText.length()) : inputDefaultText.length();
                input.setSelection(len);
            }
            if (!TextUtils.isEmpty(inputHint)) {
                input.setHint(inputHint);
            }
        }

        private void popupSoftInput(final AlertDialog dialog) {
            if (interceptAutoPopupInputMethod) {
                return;
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    EditText input = obtainEditText(dialog);
                    InputMethodManager imm = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(input, 0);
                }
            }, 100);
        }

        private void hideSoftInput(final AlertDialog dialog) {
            if (interceptAutoPopupInputMethod) {
                return;
            }
            InputMethodManager im = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            EditText input = obtainEditText(dialog);
            im.hideSoftInputFromWindow(input.getWindowToken(), 0);
        }

        /**
         * Replace {@link AlertDialog}'s inner handler by {@link ButtonHandler} via java reflection.
         * Used to intercept the inherent behavior when a button is clicked.
         */
        private void replaceHandler(AlertDialog dialog) {
            mHandler = new ButtonHandler(dialog);
            try {
                Field fController = dialog.getClass().getDeclaredField("mAlert");
                fController.setAccessible(true);
                Object controller = fController.get(dialog);
                Field fHandler = controller.getClass().getDeclaredField("mHandler");
                fHandler.setAccessible(true);
                fHandler.set(controller, mHandler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private InputDialog createInputDialog(AlertDialog dialog) {
            inputDialog = new InputDialog(dialog, obtainEditText(dialog));
            return inputDialog;
        }

        private EditText obtainEditText(AlertDialog dialog) {
            if (editTextId == 0) {
                return (EditText) dialog.findViewById(R.id.input);
            } else {
                return (EditText) dialog.findViewById(editTextId);
            }
        }

        /**
         * ButtonHandler substitute for intercepting
         */
        private static final class ButtonHandler extends Handler {
            // Button clicks have Message.what as the BUTTON{1,2,3} constant
            private static final int MSG_DISMISS_DIALOG = 1;

            private WeakReference<DialogInterface> mDialog;

            private boolean shouldInterceptButtonAction;

            public ButtonHandler(DialogInterface dialog) {
                mDialog = new WeakReference<>(dialog);
            }

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {

                    case DialogInterface.BUTTON_POSITIVE:
                    case DialogInterface.BUTTON_NEGATIVE:
                    case DialogInterface.BUTTON_NEUTRAL:
                        ((DialogInterface.OnClickListener) msg.obj).onClick(mDialog.get(), msg.what);
                        break;
                    case MSG_DISMISS_DIALOG:
                        if (shouldInterceptButtonAction) {
                            shouldInterceptButtonAction = false;
                            break;
                        }
                        ((DialogInterface) msg.obj).dismiss();
                        break;
                }
            }

            public void interceptButtonAction() {
                shouldInterceptButtonAction = true;
            }
        }
    }

    public interface ButtonActionListener {
        void onClick(CharSequence inputText);
    }

    public interface OnCancelListener {
        void onCancel(CharSequence inputText);
    }

    public interface OnDismissListener {
        void onDismiss(CharSequence inputText);
    }

    /**
     * Inherent button behavior intercepter.
     */
    public interface ButtonActionIntercepter {
        /**
         * @param whichButton The type of button, including DialogInterface.BUTTON_POSITIVE,
         *                   DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL
         * @return true if you need to intercept the inherent behavior, vise versa.
         */
        boolean onInterceptButtonAction(int whichButton, CharSequence inputText);
    }
}
